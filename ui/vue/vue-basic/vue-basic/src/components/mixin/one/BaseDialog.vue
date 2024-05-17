<template>
  <div v-if="showDialog">
    <dialog open>
      {{ message }}
      <button @click="$emit('close')">OK</button>
    </dialog>
  </div>
</template>

<script>
export default {
  props: ["message", "showDialog"],
  data() {
    return {};
  },
};
</script>

<style scoped></style>
