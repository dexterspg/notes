<template>
  <button @click="setComponent('active-goals')">Active Goals</button>
  <button @click="setComponent('manage-goals')">Manage Goals</button>

  <ActiveGoals v-if="selectedPage === 'active-goals'" />
  <ManageGoals v-if="selectedPage === 'manage-goals'" />
</template>

<script>
import ActiveGoals from "../../components/goaldynamiccomponent/one/ActiveGoals.vue";
import ManageGoals from "../../components/goaldynamiccomponent/one/ManageGoals.vue";

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
