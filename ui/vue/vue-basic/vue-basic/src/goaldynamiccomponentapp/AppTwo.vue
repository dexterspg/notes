<template>
  <button @click="setComponent('active-goals')">Active Goals</button>
  <button @click="setComponent('manage-goals')">Manage Goals</button>

  <keep-alive>
    <component :is="selectedPage"></component>
  </keep-alive>
</template>

<script>
import ActiveGoals from "../components/goaldynamiccomponent/two/ActiveGoals.vue";
import ManageGoals from "../components/goaldynamiccomponent/two/ManageGoals.vue";

export default {
  components: {
    ActiveGoals,
    ManageGoals,
  },
  data() {
    return {
      selectedPage: "active-goals",
    };
  },
  methods: {
    setComponent(page) {
      this.selectedPage = page;
    },
  },
};
</script>

<style scoped></style>
