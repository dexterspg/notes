<template>
  <div class="border">
    <slot> <p>Base Badge</p> </slot>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
};
</script>

<style scoped>
.border {
  border: 1px black solid;
}
</style>
