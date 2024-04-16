<template>
  <li  @click="$emit('deleteGoalById',id)" >
    {{ id }}
    {{ name }}
  </li>
</template>

<script>
export default {
  name: "CourseGoalItem",
  props: ["id", "name"],
  data() {
    return {};
  },
};
</script>

<style scoped>

</style>
