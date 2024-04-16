<template>
  <q-layout view="lHh lpR fFf">
    <q-header elevated class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />

        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg" />
          </q-avatar>
          My App
        </q-toolbar-title>

        <q-tabs>
          <q-tab>Podcasts</q-tab>
          <q-tab>Blog</q-tab>
          <q-tab>Videos</q-tab>
        </q-tabs>


      </q-toolbar>

      <q-tabs align="left">
        <q-route-tab to="/login" label="LOGIN" />
        <q-route-tab to="/two" label="Two Page" />
        <q-route-tab to="/three" label="Three Page" />
      </q-tabs>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      side="left"
      behavior="mobile"
      bordered
    >
      <MainLayoutMenuListVue />
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { ref } from 'vue';
import MainLayoutMenuListVue from './MainLayoutMenuList.vue';


const leftDrawerOpen = ref(false);

const toggleLeftDrawer = () => {
  leftDrawerOpen.value = !leftDrawerOpen.value;
};
</script>
