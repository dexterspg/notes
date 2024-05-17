<template>
  <UserAlert v-if="alertIsVisible" title="Delete User" @close="hideAlert">
    <p>Do you want to delete User?</p>
  </UserAlert>

  <section>
    <h2>Delete User</h2>
    <button @click="showAlert">Delete User</button>
  </section>
</template>

<script>
import UserAlert from "./UserAlert.vue";

export default {
  components: {
    UserAlert,
  },
  data() {
    return {
      alertIsVisible: false,
    };
  },
  methods: {
    showAlert() {
      this.alertIsVisible = true;
    },
    hideAlert() {
      this.alertIsVisible = false;
    },
  },
};
</script>

<style scoped></style>
