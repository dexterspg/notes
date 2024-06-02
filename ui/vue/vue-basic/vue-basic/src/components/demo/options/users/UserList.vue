<template>
    <h1>Active Users</h1>
    <UserItem 
        v-for="user in users"
        :key="user.id"
        :name="user.name"
    />
</template>

<script>
import UserItem from "./UserItem.vue";
import { mapGetters } from "vuex";
export default {
  components: {
    UserItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["users"]),
  },
};
</script>

<style scoped></style>
