<template>
  <TheHeader title="Routing" />
  <TheNavigation />
  <main>
    <router-view />
  </main>
  <footer>
    <router-view name="footer"/>
  </footer>
</template>

<script>
import TheHeader from "../../components/TheHeader.vue";
import TheNavigation from "../../components/teamusers/one/TheNavigation.vue";
import teamsData from "./teams/teams.js";
import usersData from "./users/users.js";

export default {
  components: {
    TheHeader,
    TheNavigation,
  },
  provide() {
    return {
      teams: teamsData,
      users: usersData,
    };
  },
  data() {
    return {
      teams: teamsData,
      users: usersData,
    };
  },
};
</script>

<style scoped></style>
