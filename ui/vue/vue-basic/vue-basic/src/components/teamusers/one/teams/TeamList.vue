<template>
  <h1>Team List</h1>

  <TeamItem
    v-for="team in teams"
    :key="team.id"
    :id="team.id"
    :name="team.name"
    :memberCount="team.members.length"
  />

</template>

<script>
import TeamItem from "./TeamItem.vue";

export default {
  components: {
    TeamItem,
  },
  inject: ["teams"],
  data() {
    return {};
  },
};
</script>

<style scoped></style>
