<template>
  <form @submit.prevent>
    <input v-model="newGoal" />
    <pre>{{ newGoal }}</pre>
    <button @click="$emit('addGoal', newGoal)">Add Goal</button>
  </form>

  <p v-if="goals.length === 0">No goals found</p>
  <ul else>
    <li v-for="goal in goals" @click="deleteGoalById(goal.id)" :key="goal.id">
      {{ goal.id }}
    {{
      goal.name
    }}
    </li>
  </ul>
</template>

<script>
export default {
  name: "CourseGoals",
  components: {},
  props: ["goals"],
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
