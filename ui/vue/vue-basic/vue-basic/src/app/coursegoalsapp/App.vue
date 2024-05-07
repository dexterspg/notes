<template>
  <GlobalComponent />
  <TheHeader :title="title" />

  <pre>{{ message }}</pre>
  <input type="text" ref="inputMessage" />
  <button @click="message = $refs.inputMessage.value">Enter Message</button>

  <form @submit.prevent>
    <input v-model="newGoal" />
    <pre>{{ newGoal }}</pre>
    <button @click="addGoal">Add Goal</button>
  </form>

  <p v-if="goals.length === 0">No goals found</p>
  <ul else>
    <li
      v-for="(goal, index) in goals"
      :key="goal.id"
    >
      {{ index }} : {{ goal.id }} {{ goal.name }}
      <button @click="deleteGoalById(goal.id)">Delete</button>
      <div>
        <input type="text" />
        <button>submit</button>
      </div>
    </li>
  </ul>
</template>

<script>
import TheHeader from "../../components/TheHeader.vue";

export default {
  name: "App",
  components: {
    TheHeader,
  },
  data() {
    return {
      message: "",
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
      if (idx !== -1) this.goals.splice(idx, 1);
    },
    setMessage(message) {
      console.log(message);
      this.message = message;
    },
  },
  beforeCreate() {
    console.log("beforeCreate");
  },
  created() {
    console.log("created-nothing on screen");
  },
  beforeMount() {
    console.log("beforeMount-before creating something on screen");
  },
  mounted() {
    console.log("mount-show vue instance");
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
  beforeUpdate() {
    console.log("beforeUpdate-data change detected");
    console.log(this.goals.length + " ::: " + this.newGoal + ":::") +
      this.message;
  },
  updated() {
    console.log("updated");
    console.log(this.goals.length + " ::: " + this.newGoal + ":::") +
      this.message;
  },
  unmounted() {
    console.log("unmount");
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
