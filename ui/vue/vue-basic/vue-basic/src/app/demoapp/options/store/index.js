import { createStore } from "vuex";
import users from "../../users/users.js";

const store = createStore({
  state() {
    return {
      users: users,
      projects: [],
    };
  },
  getters: {
    users(state) {
      return state.users;
    },
    projects(state) {
      return state.projects;
    },
  },
  mutations: {
    getProductsByUser(state, payload) {
      console.log("Paylod is" + payload);
      const user = state.users.find((u) => u.id === payload.id);
      state.projects = user.projects;
      // return state.users.filter((u) => u.id === payload.id);
    },
  },
});

export default store;
