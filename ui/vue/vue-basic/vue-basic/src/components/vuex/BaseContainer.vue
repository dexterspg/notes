<template>
    <h2>{{ title }}</h2>
  <slot>
        <p>No button</p>
  </slot>
</template>

<script>
export default {
  props: ["title"],
  data() {
    return {
        };
  },
    methods: {
    }
};
</script>

<style scoped></style>
