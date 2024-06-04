<template>
  <main>
    <UserList />
    <ProjectList />
  </main>
</template>

<script>
import UserList from "../../../components/demo/options/users/UserList.vue";
import ProjectList from "../../../components/demo/options/projects/ProjectList.vue";

export default {
  components: {
    UserList,
    ProjectList,
  },
  data() {
    return {};
  },
};
</script>

<style scoped></style>
