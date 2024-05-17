<template>
  <UserAlert v-if="alertIsVisible" title="Add User" @close="hideAlert">
    <p>Do you want to add User?</p>
  </UserAlert>

  <section>
    <h2>Add User</h2>
    <button @click="showAlert">Add User</button>
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
