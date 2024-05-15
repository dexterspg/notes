export default {
  //mutations must be synchronized
  //to be sure components will not contain commit
  //actions should have the commit
  incrementTwo(state) {
    state.count = state.count + 2;
  },
  incrementWithPayLoad(state, payload) {
    state.count = state.count + payload.myValue;
  },
};
