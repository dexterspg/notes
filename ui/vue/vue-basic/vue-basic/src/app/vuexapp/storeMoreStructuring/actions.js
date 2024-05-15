export default {
    //uses dispatch in component
    incrementTwo(context) {
      console.log("incrementTwo: context");
      console.log(context);
      setTimeout(() => context.commit("incrementTwo"), 3000);
    },
    incrementWithPayLoad(context, payload) {
      context.commit("incrementWithPayLoad", payload);
    },
};
