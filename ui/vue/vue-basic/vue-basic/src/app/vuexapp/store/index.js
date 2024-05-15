import { createStore } from "vuex";
import products from "./modules/products.js";

const globalStateModule = {
  state() { //global state not accessible to the store, hence, use getters
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

const store = createStore({//add to main.js in app.use
  modules: { //modules declare here are global
    globalStateMod: globalStateModule,
    globModNmSpc: globalStateModuleWithNameSpaceEnabled,
  },
    //below are example of local module
  state() {
    return {
      count: 0,
      products: products,
    };
  },
  mutations: {
    //mutations must be synchronized
    //to be sure components will not contain commit
    //actions should have the commit
    incrementTwo(state) {
      state.count = state.count + 2;
    },
    incrementWithPayLoad(state, payload) {
      state.count = state.count + payload.myValue;
    },
  },
  actions: {
    //uses dispatch in component
    incrementTwo(context) {
      console.log("incrementTwo: context");
      console.log(context);
      setTimeout(() => context.commit("incrementTwo"), 3000);
    },
    incrementWithPayLoad(context, payload) {
      context.commit("incrementWithPayLoad", payload);
    },
  },
  getters: {
    //similar to computed
    duplicateCount(state) {
      return state.count * 2;
    },
    // checkOverTen(state, getters) {
    checkOverTen(_, getters) {
      //can replace param not used to _
      if (getters.duplicateCount > 10) {
        return true;
      } else {
        return false;
      }
    },
  },
});

export default store;
