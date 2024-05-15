export default {
  setActionExampleMessage(context, payload) {
    console.log("setActionExampleMessage");
    context.commit("mutateExampleMessage", payload);
  },
};
