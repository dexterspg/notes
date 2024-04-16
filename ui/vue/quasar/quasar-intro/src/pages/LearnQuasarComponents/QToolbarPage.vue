<template>
  <q-page padding>

    <div class="bg-primary text-white shadow-1">
      <q-toolbar >
        <q-toolbar-title>
          Quasar App
        </q-toolbar-title>

        <q-btn
          flat
          round
          icon="settings"
        />
      </q-toolbar>


      <q-toolbar
        inset
      >
        Home / Components / Toobar
      </q-toolbar>
    </div>

    <q-dialog :model-value="true">

      <q-card>
      <q-toolbar class="bg-secondary text-white">
        <q-toolbar-title>More Info...</q-toolbar-title>

          <q-btn
            v-close-popup
            flat
            round
            icon="close"
          />
      </q-toolbar>
        <q-card-section>
          Lorem ipsum dolor sit amet, qui minim labore adipisicing minim sint cillum sint consectetur cupidatat.

        </q-card-section>

      </q-card>
    </q-dialog>


  </q-page>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
  // name: 'PageName'
});
</script>
