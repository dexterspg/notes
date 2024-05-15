import exampleGetters from "./getters.js";
import exampleMutations from "./mutations.js";
import exampleActions from "./actions.js";

export default {
  namespaced: true,
  state() {
    return {
      exampleMessage: "This is an example message",
    };
  },
  getters: exampleGetters,
  mutations: exampleMutations,
  actions: exampleActions,
};
