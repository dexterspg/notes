<template>
  <div>
    <slot> Default </slot>
  </div>
  <div v-if="$slots.subheading">
    <slot name="subheading" class="subhead">
      <p>Base card Header slot no content</p>
    </slot>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  mounted() {
    console.log(this.$slots.subheading);
  },
};
</script>

<style scoped>
div {
  border: red 1px solid;
}

p {
  color: green;
}
</style>
