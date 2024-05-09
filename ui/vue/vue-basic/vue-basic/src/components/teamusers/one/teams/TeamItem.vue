<template>
  <h3>{{ name }}</h3>
  <p>{{ memberCount }} members</p>
  <!-- <button @click="viewMembers">View Members</button> -->
    <router-link :to="viewMembers">
        View Members
    </router-link>
</template>


<script>
export default {
  props: ["id", "name", "memberCount"],
  data() {
    return {};
  },
  // methods: {
  //   viewMembers() {
  //     this.$router.push("/teams/" + this.id);
  //   },
  // },
  computed: {
    viewMembers() {
      return "/teams/" + this.id;
    },
  },
};
</script>

<style scoped></style>
