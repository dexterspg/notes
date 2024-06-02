<template>
  <pre>ProjectList</pre>
  <p>{{ getProjectsByUser() }}</p>
</template>

<script>
// import { mapGetters } from "vuex";
export default {
  data() {
    return {};
  },
  methods: {
    // ...mapGetters(["getProjectsByUser"]),
    getProjectsByUser() {
      this.$store.commit("getProjectsByUser", { id: "u1" });
      return this.$store.getters.projects;
    },
  },
};
</script>

<style scoped></style>
