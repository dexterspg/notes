<template>
  <BaseContainer>
    <BaseSearch />
    <UserList />
  </BaseContainer>
  <BaseContainer>
    <BaseSearch />
    <ProjectList />
  </BaseContainer>
</template>

<script>
import UserList from "../../../components/demo/options/users/UserList.vue";
import ProjectList from "../../../components/demo/options/projects/ProjectList.vue";
import BaseSearch from "../../../components/demo/options/UI/BaseSearch.vue";
import BaseContainer from "../../../components/demo/options/UI/BaseContainer.vue";

export default {
  components: {
    UserList,
    ProjectList,
    BaseSearch,
    BaseContainer,
  },
  data() {
    return {};
  },
};
</script>

<style scoped></style>
