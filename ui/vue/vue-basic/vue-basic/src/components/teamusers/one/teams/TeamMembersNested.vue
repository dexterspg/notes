<template>
  <h2>{{ teamName }}</h2>

  <button @click="goToTeams">Go To Teams</button>

  <UserItem
    v-for="member in members"
    :key="member.id"
    :id="member.id"
    :name="member.name"
  />

  <router-link to="/teams/three/t2">go to team 2</router-link>
</template>

<script>
import UserItem from "../users/UserItem.vue";
export default {
  components: {
    UserItem,
  },
  props: ["teamId"],
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
    loadMembers(teamId) {
      console.log(teamId);
      const foundTeam = this.teams.find((e) => e.id === teamId);
      const members = [];
      foundTeam.members.forEach((id) => {
        const user = this.users.find((u) => u.id === id);
        members.push(user);
      });
      this.teamName = foundTeam.name;
      this.members = members;
    },
  },
  created() {
    this.loadMembers(this.teamId);
  },
  watch: {
    teamId(id) {
      this.loadMembers(id);
    },
  },
};
</script>

<style scoped></style>
