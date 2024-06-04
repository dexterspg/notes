<template>
  <BaseContainer>
    <h1>Active Users</h1>
    <BaseSearch filterType="filterUsers" />
    <UserItem
      v-for="user in filteredUsers"
      :key="user.id"
      :id="user.id"
      :name="user.name"
    />
  </BaseContainer>
</template>

<script>
import UserItem from "./UserItem.vue";
import BaseSearch from "../UI/BaseSearch.vue";
import BaseContainer from "../UI/BaseContainer.vue";
import { mapGetters } from "vuex";
export default {
  components: {
    UserItem,
    BaseSearch,
    BaseContainer,
  },
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["filteredUsers"]),
  },
};
</script>

<style scoped></style>
