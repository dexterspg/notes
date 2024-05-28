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
import { ref } from "vue";

export default {
  components: {
    UserAlert,
  },
  setup() {
    const alertIsVisible = ref(false);
    const showAlert = () => (alertIsVisible.value = true);
    const hideAlert = () => (alertIsVisible.value = false);
    return {
      alertIsVisible,
      showAlert,
      hideAlert,
    };
  },
};
</script>

<style scoped></style>
