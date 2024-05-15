export default {
  mutateExampleMessage(state, payload) {
    console.log("mutateExampleMessage");
    state.exampleMessage = payload.addedMessage + state.exampleMessage;
  },
};
