import { createStore } from "vuex";
import products from "./modules/product/products.js";
import exampleModule from "./modules/example/index.js";

import rootMutations from "./mutations.js";
import rootActions from "./actions.js";
import rootGetters from "./getters.js";

const globalStateModule = {
  state() {
    //global state not accessible to the store, hence, use getters
    return {
      globalStateMessage: "This is a global state",
    };
  },
  getters: {
    getGlobalStateMessage(state) {
      return state.globalStateMessage;
    },
  },
};

const globalStateModuleWithNameSpaceEnabled = {
  namespaced: true,
  state() {
    return {
      globalStateMessage: "This is a global state using  namespace",
    };
  },
  getters: {
    getGlobalStateMessage(state) {
      return state.globalStateMessage;
    },
  },
};

const store = createStore({
  //add to main.js in app.use
  modules: {
    //modules declare here are global
    globalStateMod: globalStateModule,
    globModNmSpc: globalStateModuleWithNameSpaceEnabled,
    exampleModule,
  },
  //below are example of local module
  state() {
    return {
      count: 0,
      products: products,
    };
  },
  mutations: rootMutations,
  actions: rootActions,
  getters: rootGetters,
});

export default store;
