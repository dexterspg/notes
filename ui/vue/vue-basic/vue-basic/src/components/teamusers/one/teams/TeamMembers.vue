<template>
  <h2>{{ teamName }}</h2>

  <button @click="goToTeams">Go To Teams</button>

  <UserItem
    v-for="member in members"
    :key="member.id"
    :id="member.id"
    :name="member.name"
  />

  <router-link to="/teams/t2">go to team 2</router-link>
</template>

<script>
import UserItem from "../users/UserItem.vue";
export default {
  components: {
    UserItem,
  },
  inject: ["teams", "users"],
  data() {
    return {
      teamName: "Unnamed Team",
      members: [],
    };
  },
  methods: {
    goToTeams() {
      this.$router.push("/teams");
    },
    loadMembers(route) {
      const teamId = route.params.teamId;

      const foundTeam = this.teams.find((e) => e.id === teamId);
      this.teamName = foundTeam.name;
      const members = [];
      foundTeam.members.forEach((id) => {
        const user = this.users.find((u) => u.id === id);
        members.push(user);
      });
      this.members = members;
    },
  },
  created() {
    this.loadMembers(this.$route);
  },
  watch: {
    $route(newValue) {
      this.loadMembers(newValue);
    },
  },
};
</script>

<style scoped></style>
