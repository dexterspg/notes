<template>
  <q-page
    class="row justify-center"
    padding>
    <div
      v-for="item in inventory"
    :key="item.id"
      class="col full-width"
      style="max-width: 450px; min-width:450px;"
    >
    <q-card
      class="cursor-pointer q-ma-md"
   >
      <q-img
        :src="item.imageSrc"
      />

      <h5 class="q-mb-none q-mt-sm q-ml-md text-grey-9"
      >
        {{ item.label }}
      <q-chip
          v-for="category in item.categories"
          :key="category.id"
          :color="category.color"
          :label="category.name"
          text-color="white"
          size="sm"
        />

      </h5>


      <q-card-section >
        {{ item.description }}
      </q-card-section>

    </q-card>
    </div>
  </q-page>
</template>


<script setup>

const inventory = [
  {
    id: 1,
    label: 'Spicy Pants',
    description : 'Look greater in any era!',
    imageSrc : 'http://picsum.photos/300/180',
    categories : [
      {
        name : 'pants',
        color: 'green'
      },
      {
        name :'clothes',
        color: 'blue'
      }
    ]
  },
  {
    id: 2,
    label: 'Classic Shirt',
    description : 'Timeless elegance for every occasion.',
    imageSrc : 'http://picsum.photos/300/180',
    categories : [
      {
        name : 'shirt',
        color: 'purple'
      },
      {
        name :'clothes',
        color: 'blue'
      }
    ]
  },
  {
    id: 3,
    label: 'Casual Sneakers',
    description : 'Comfort meets style in every step.',
    imageSrc : 'http://picsum.photos/300/180',
    categories : [
      {
        name : 'shoes',
        color: 'black'
      },
      {
        name :'footwear',
        color: 'yellow'
      }
    ]
  },
  {
    id: 4,
    label: 'Cozy Sweater',
    description : 'Stay warm and stylish on chilly days.',
    imageSrc : 'http://picsum.photos/300/180',
    categories : [
      {
        name : 'sweater',
        color: 'brown'
      },
      {
        name :'clothes',
        color: 'blue'
      }
    ]
  }
];


</script>
