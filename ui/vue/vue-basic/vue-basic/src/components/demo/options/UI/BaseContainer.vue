<template>
  <div>
    <slot> </slot>
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
div {
  border: 1px solid black;
  width: 40%;
  padding: 1%;
  margin: 2%;
  display: inline-block;
}
</style>
