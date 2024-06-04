<template>
  <pre>BaseSearch</pre>
  <input type="text" placeholder="Filter Items" v-model="enteredText" />
  <div>
    <button @click="sortAscending">Sort Ascending</button>
    <button @click="sortDescending">Sort Descending</button>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  props: ["filterType"],
  data() {
    return {
      enteredText: "",
    };
  },
  watch: {
    enteredText(val) {
      if (this.filterType != null) {
        this.$store.dispatch(this.filterType, { enteredText: val });
      }
    },
  },
  methods: {
    // sortAscending() {
    //   this.$store.dispatch("sortAscending");
    // },
    // sortDescending() {
    // this.$store.dispatch("sortDescending");
    // },
    ...mapActions(["sortAscending","sortDescending"]),
  },
};
</script>

<style scoped></style>
