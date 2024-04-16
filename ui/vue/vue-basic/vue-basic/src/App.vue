<template>
  <TheHeader :title="title" />

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
import TheHeader from "./components/TheHeader.vue";

export default {
  name: "App",
  components: {
    TheHeader,
  },
  data() {
    return {
      title: "My Course Goals",
      newGoal: "",
      goals: [],
    };
  },
  methods: {
    addGoal() {
      let currId = 1;

      for (let i = 1; i <= this.goals.length; ++i) {
        if (!this.goals.find((item) => item.id === i)) {
          console.log(currId);
          currId = i;
          break;
        }
        currId = i + 1;
      }

      this.goals.push({ id: currId, name: this.newGoal });

      this.newGoal = "";
    },
    deleteGoalById(id) {
      console.log("Deleting id :::" + id);
      const idx = this.goals.findIndex((item) => item.id === id);
      if(idx !== -1) this.goals.splice(idx , 1);
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
