<template>
  <TheHeader title="Badge App" />
  <BadgeList />
  <UserInfo />
</template>

<script>
import TheHeader from "../components/TheHeader.vue";
import BadgeList from "../components/badge/one/BadgeList.vue";
import UserInfo from "../components/badge/one/UserInfo.vue";

export default {
  components: {
    TheHeader,
    BadgeList,
    UserInfo,
  },
  provide() {
    return {
      users: this.users,
    };
  },
  data() {
    return {
      users: [
        {
          id: 1,
          name: "Bob",
          description: "an aspiring author",
          role: "author",
        },
        {
          id: 1,
          name: "Jane",
          description: "IT admin",
          role: "admin",
        },
      ],
    };
  },
};
</script>

<style scoped></style>
