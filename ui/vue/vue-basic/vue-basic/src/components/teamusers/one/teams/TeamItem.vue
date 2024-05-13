<template>
  <h3>{{ name }}</h3>
  <p>{{ memberCount }} members</p>
  <!-- <button @click="viewMembers">View Members</button> -->
  <div>
    <router-link :to="'/teams/one/' + id"> View Members </router-link>
  </div>

  <div>
    <router-link :to="viewMembersProps"> View Members Using props </router-link>
  </div>

  <div>
    <router-link :to="viewMembersNested"> View Members Nested </router-link>
  </div>

  <div>
    <router-link :to="viewMembersReturnPath">
      View Members Return Path
    </router-link>
  </div>

  <div>
    <router-link :to="viewMembersNamedRoute">
      View Members Named Route
    </router-link>
  </div>
  <div>
    <router-link :to="viewMembersNamedRouteWithQueryParam">
      View Members Named Route with query param
    </router-link>
  </div>
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
    viewMembersProps() {
      return "/teams/two/" + this.id;
    },
    viewMembersNested() {
      return "/teams/three/" + this.id;
    },
    viewMembersReturnPath() {
      return { path: "/teams/three/" + this.id };
    },
    viewMembersNamedRoute() {
      // this.$router.push({ name: "team-members", params: { teamId: this.id } });
      return { name: "team-members", params: { teamId: this.id } };
    },

    viewMembersNamedRouteWithQueryParam() {
      //query not working
      return {
        name: "team-members",
        params: { teamId: this.id },
        query: { sort: "asc" },
      };
    },
  },
};
</script>

<style scoped></style>
