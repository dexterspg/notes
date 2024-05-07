<template>
  <h1>Manage Goal</h1>
  <input type="text" ref="goal" />
  <button @click="setGoal">Set Goal</button>
  <teleport to="body">
    <ErrorAlert v-if="invalidInput" errorMessage="Input is Empty">
      <p>You did not enter any text</p>
      <button @click="closeError">OK</button>
    </ErrorAlert>
  </teleport>
</template>

<script>
import ErrorAlert from "./ErrorAlert.vue";

export default {
  components: {
    ErrorAlert,
  },
  data() {
    return {
      enteredInput: "",
      invalidInput: false,
    };
  },
  methods: {
    setGoal() {
      const textInput = this.$refs.goal.value;
      if (textInput === "") {
        this.invalidInput = true;
      }
    },
    closeError() {
      this.enteredInput = "";
      this.invalidInput = false;
    },
  },
};
</script>

<style scoped></style>
