<template>
  <form @submit.prevent>
    <input v-model="newGoal" />
    <pre>{{ newGoal }}</pre>
    <button @click="addGoal">Add Goal</button>
  </form>

  <p v-if="goals.length === 0">No goals found</p>
  <ul else>
    <li v-for="goal in goals" @click="deleteGoalById(goal.id)" :key="goal.id">
      {{ goal.id }}
      {{ goal.name }}
    </li>
  </ul>
</template>

<script>
export default {
  name: "CourseGoals",
  components: {},
  props: ["goals"],
  emits: ["addGoal", "deleteGoalById"],
  data() {
    return {
      newGoal: "",
    };
  },
  methods: {
    addGoal() {
      this.$emit("addGoal", {
        id: new Date().toISOString(),
        name: this.newGoal,
      });
    },
    deleteGoalById(id) {
      this.$emit("deleteGoalById", id);
    },
  },
};
</script>

<style scoped></style>
