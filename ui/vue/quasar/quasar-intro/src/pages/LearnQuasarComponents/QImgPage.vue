<template>
  <q-page class="flex flex-center">
    <q-img
      :src="imageSrc"
    >
      <div class="absolute-top-left text-center text-subtitle1">
      A Beautiful Day
    </div>

    </q-img>
    <q-btn
      label="change"
      @click="imageSrc ='https://picsum.photos/850/420'">
    </q-btn>
  </q-page>
</template>

<script  setup>
import { ref } from 'vue';

const imageSrc = ref('https://picsum.photos/800/400')
</script>
