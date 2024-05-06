<template>
  <li @click="$emit('deleteGoalById', id)">
    {{ id }}
    {{ name }}
  </li>
</template>

<script>
export default {
  name: "CourseGoalItem",
  props: ["id", "name"],
  emits: ["deleteGoalById"],
  data() {
    return {};
  },
};
</script>

<style scoped></style>
