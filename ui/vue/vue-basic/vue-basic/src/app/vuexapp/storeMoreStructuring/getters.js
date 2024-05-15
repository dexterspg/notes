export default {
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
};
