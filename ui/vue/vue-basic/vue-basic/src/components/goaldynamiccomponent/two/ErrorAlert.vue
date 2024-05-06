<template>
  <dialog open>
    <slot>
      <p>{{ errorMessage}}</p>
    </slot>
  </dialog>
</template>

<script>
export default {
  props: ["errorMessage"],
  data() {
    return {};
  },
  methods: {
    close() {},
  },
};
</script>

<style scoped></style>
