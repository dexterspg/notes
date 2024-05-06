<template>
  <TheHeader :title="title" />
  <CourseGoals
    :goals="goals"
    @addGoal="addGoal"
    @deleteGoalById="deleteGoalById"
  />
</template>

<script>
import TheHeader from "../components/TheHeader.vue";
import CourseGoals from "../components/coursegoals/two/CourseGoals.vue";

export default {
  name: "App",
  components: {
    TheHeader,
    CourseGoals,
  },
  data() {
    return {
      title: "My Course Goals",
      goals: [],
    };
  },
  methods: {
    addGoal(newGoal) {
      let currId = 1;

      for (let i = 1; i <= this.goals.length; ++i) {
        if (!this.goals.find((item) => item.id === i)) {
          console.log(currId);
          currId = i;
          break;
        }
        currId = i + 1;
      }

      this.goals.push({ id: currId, name: newGoal });
      this.newGoal = "";
    },
    deleteGoalById(id) {
      console.log("Deleting id :::" + id);
      const idx = this.goals.findIndex((item) => item.id === id);
      if (idx !== -1) this.goals.splice(idx, 1);
    },
  },
  mounted() {
    this.goals = [
      {
        id: 1,
        name: "Study Java",
      },
      {
        id: 2,
        name: "Learn Spring Boot",
      },
      {
        id: 3,
        name: "Familiarize with Vue",
      },
    ];
    // this.goals=[];
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
li {
  list-style-type: none;
}

li:hover {
  color: red;
}
</style>
