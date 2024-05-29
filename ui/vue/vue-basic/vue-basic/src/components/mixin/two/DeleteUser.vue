<template>
  <UserAlert v-if="alertIsVisible" :title="alertTitle" @close="hideAlert">
    <p>Do you want to delete User?</p>
  </UserAlert>

  <section>
    <h2>Delete User</h2>
    <button @click="showAlert">Delete User</button>
  </section>
</template>

<script>
import UserAlert from "./UserAlert.vue";
import useAlert from "./hooks/alert.js";
import { ref } from "vue";

export default {
  components: {
    UserAlert,
  },
  setup() {
    const alertTitle = ref("Delete User");
    const [alertIsVisible, showAlert, hideAlert] = useAlert(false);

    return {
      alertTitle,
      alertIsVisible,
      showAlert,
      hideAlert,
    };
  },
};
</script>

<style scoped></style>
