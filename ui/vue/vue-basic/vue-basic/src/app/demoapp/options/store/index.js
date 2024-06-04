import { createStore } from "vuex";
import users from "../../users/users.js";

const store = createStore({
  state() {
    return {
      users: users,
      projects: [],
      filteredProjects: [],
      filteredUsers: users,
    };
  },
  getters: {
    users(state) {
      return state.users;
    },
    projects(state) {
      console.log(state.filteredProjects);
      return state.filteredProjects;
    },
    filteredUsers(state) {
      return state.filteredUsers;
    },
  },
  mutations: {
    filterUsers(state, payload) {
      const filteredUsers = [];
      state.users
        .filter((u) =>
          u.name.toLowerCase().includes(payload.enteredText.toLowerCase())
        )
        .forEach((e) => {
          filteredUsers.push(e);
        });
      state.filteredUsers = filteredUsers;
    },
    sortAscending(state) {
      state.filteredUsers.sort((a, b) => a.name.localeCompare(b.name));
    },
    sortDescending(state) {
      state.filteredUsers.sort((a, b) => b.name.localeCompare(a.name));
    },
    filterProjects(state, payload) {
      const user = state.filteredUsers.find((u) => u.id === payload.id);
      const filteredProjects = user.projects;
      state.filteredProjects = filteredProjects;
      console.log(state.filteredProjects);
    },
  },
  actions: {
    filterUsers(context, payload) {
      context.commit("filterUsers", payload);
    },
    sortAscending(context) {
      context.commit("sortAscending");
    },
    sortDescending(context) {
      context.commit("sortDescending");
    },
  },
});

export default store;
