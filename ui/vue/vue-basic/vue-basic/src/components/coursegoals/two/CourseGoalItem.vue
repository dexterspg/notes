<template>
    Hello
  <li @click="$emit('deleteGoalById', id)">
    {{ id }}
    {{ name }}
  </li>
</template>

<script>
export default {
  name: "CourseGoalItem",
  props: ["id", "name"],
  // props: {
  //   id: {
  //     required: true,
  //     default: new Date().getTime(),
  //     validator: (value) => value > 0,
  //   },
  //   name: {
  //     type: String,
  //     required: true,
  //   },
  // },
};
</script>

<style scoped></style>
