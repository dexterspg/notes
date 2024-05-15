<template>
  <div>
    <button @click="addTwoMutation">Add 2 Mutation</button>
    <button @click="addTwoAction">Add 2 Action</button>
    <button @click="incrementTwo">Add 2 mapActions</button>
    <button @click="incTwo">Add 2 mapActions name change</button>
  </div>

  <div>
    <input type="number" ref="payload" />
    <button @click="addMutationPayload">Add Payload Mutation</button>
    <button @click="addActionPayload">Add Payload Action</button>
    <button
      @click="incrementWithPayLoad({ myValue: Number($refs.payload.value) })"
    >
      Add Payload using mapActions
    </button>
  </div>
  <div>
    <h3>Duplicate count (using getters): {{ duplicateCountWithGetter }}</h3>
    <p>Check over 10 : {{ checkCountOver10WithGetterWithGetterArg }}</p>
    <p>Duplicate count using mapGetters : {{ duplicateCount }}</p>
    <p>Duplicate count using mapGetters with rename : {{ dupCount }}</p>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { mapActions } from "vuex";

export default {
  data() {
    return {
      payload: 0,
    };
  },
  methods: {
    addOne() {
      this.$store.state.count++; //not good practice
    },
    addTwoMutation() {
      //use action or dispatch
      this.$store.commit("incrementTwo");
    },
    addMutationPayload() {
      // this.$store.commit("incrementWithPayLoad",  10 );
      // this.$store.commit("incrementWithPayLoad", { myValue : 10 });
      // this.$store.commit("incrementWithPayLoad", {
      // myValue : Number(this.$refs.payload.value),
      // });
      this.$store.commit({
        type: "incrementWithPayLoad",
        myValue: Number(this.$refs.payload.value),
      });
    },
    addTwoAction() {
      this.$store.dispatch("incrementTwo");
    },
    addActionPayload() {
      this.$store.dispatch({
        type: "incrementWithPayLoad",
        myValue: Number(this.$refs.payload.value),
      });
    },
    ...mapActions(["incrementTwo", "incrementWithPayLoad"]),
    //can also assign name by
    ...mapActions({
      incTwo: "incrementTwo",
      incWithPayLoad: "incrementWithPayLoad",
    }),
  },
  computed: {
    duplicateCountWithGetter() {
      // return this.$store.state.count * 2; //if reuse needs to replace a lot of code
      return this.$store.getters.duplicateCount; //better to store in one file and reuse
    },
    checkCountOver10WithGetterWithGetterArg() {
      return this.$store.getters.checkOverTen;
    },
    ...mapGetters(["duplicateCount"]),
    ...mapGetters({
      dupCount: "duplicateCount",
    }),
  },
};
</script>

<style scoped></style>
