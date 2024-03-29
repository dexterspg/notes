<template>
  <q-page
    class="flex flex-center"
    padding
  >
    <q-card
      class="full-width"
      style="max-width: 370px;"
    >
      <q-card-section
        style="font-size: 1.3em"
        class="text-center"
      >
       Login
      </q-card-section>

      <q-card-section>
        <q-input
          v-model="form.email"
          outlined
          label="Email"
          type="email"
          class="q-mb-md"
        />
        <q-input
          v-model="form.password"
          outlined
          label="Password"
          type="password"
        />
      </q-card-section>

      <q-btn
      class="full-width"
      label="Login"
      color="primary"
        @click="login"
      />
    </q-card>
  </q-page>
</template>

<script setup lang="ts">
import { ref } from 'vue';

    const form = ref({
      email: '',
      password: ''

    });

    const login = () => {
  console.log(form.value);
    }


</script>
