<template>
  <q-list>
    <q-item
      class="bg-primary text-white"
      to="/users">
      <q-item-section>
        <q-item-label>
        Menu
        </q-item-label>
      </q-item-section>
    </q-item>
    <q-item
      v-for="item in menuItems"
      :key="item.label"
      :to="item.to"
      >
      <q-item-section>
        <q-item-label>
          {{ item.label }}
        </q-item-label>
      </q-item-section>
    </q-item>
  </q-list>
</template>

<script setup >
import menuItems from './menuItems';

</script>
