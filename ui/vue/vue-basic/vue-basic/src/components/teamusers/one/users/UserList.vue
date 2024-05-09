<template>
  <UserItem v-for="user in users"
        :key="user.id"
        :id="user.id"
        :name="user.name"
    />
</template>

<script>
import UserItem from "./UserItem.vue";

export default {
  components: {
    UserItem,
  },
  inject: ["users"],
  data() {
    return {};
  },
};
</script>

<style scoped></style>
