<template>
    <h2>{{ title }}</h2>
  <slot :count="count"  :increment="increment">
        <p>No button</p>
  </slot>
</template>

<script>
export default {
  props: ["title"],
  data() {
    return {
            count : 4
        };
  },
    methods: {
        increment(){
            this.count++;
        }
    }
};
</script>

<style scoped></style>
