<template>
  <form @submit.prevent>
    <input v-model="newGoal" />
    <pre>{{ newGoal }}</pre>
    <button @click="$emit('addGoal', newGoal)">Add Goal</button>
  </form>

  <p v-if="goals.length === 0">No goals found</p>
  <ul else>
    <CourseGoalItem
      v-for="goal in goals"
      :key="goal.id"
      :id="goal.id"
      :name="goal.name"
      @deleteGoalById="deleteGoalById"
    />
  </ul>
</template>

<script>
import CourseGoalItem from "./CourseGoalItem.vue";

export default {
  name: "CourseGoals",
  components: {
    CourseGoalItem,
  },
  inject: ["goals"],
  emits: ["deleteGoalById"],

  data() {
    return {
      newGoal: "",
    };
  },
  methods: {
    deleteGoalById(id) {
      this.$emit("deleteGoalById", id);
    },
  },
};
</script>

<style scoped></style>
