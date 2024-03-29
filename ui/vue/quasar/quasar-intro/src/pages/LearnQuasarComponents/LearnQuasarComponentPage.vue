<template>
  <q-page padding>
    <q-list
    >
      <q-item
        v-for="item in learnqcomponents"
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

  </q-page>
</template>

<script setup>

import learnqcomponents from './learnqcomponents';


</script>
