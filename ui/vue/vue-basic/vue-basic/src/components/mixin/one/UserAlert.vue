<template>
  <div class="backdrop">
    <dialog open>
      <header>
        {{ title }}
      </header>
      <div>
        <slot> </slot>
      </div>
      <menu>
        <button @click="closeDialog">Close</button>
      </menu>
    </dialog>
  </div>
</template>

<script>
export default {
  props: ["title", "message"],
  emits: ["close"],
  data() {
    return {};
  },
  methods: {
    closeDialog() {
      this.$emit("close");
    },
  },
};
</script>

<style scoped>
header {
  background-color: purple;
  width: 100%;
}
</style>
