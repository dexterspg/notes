<template>
  <ul>
    <div>
      <li :class="{ active: modelValue === 'poor' }">
        <button type="button" @click="selectRating('poor')">Poor</button>
      </li>
    </div>

    <div>
      <li :class="{ active: modelValue === 'average' }">
        <button type="button" @click="selectRating('average')">Average</button>
      </li>
    </div>

    <div>
      <li :class="{ active: modelValue === 'great' }">
        <button type="button" @click="selectRating('great')">Great</button>
      </li>
    </div>
  </ul>
</template>

<script>
export default {
  props: ["modelValue"],
  emits: ["update:modelValue"],
  data() {
    return {
      // activeOption: this.modelValue,
    };
  },
  // computed: {
  // activeOption() {
  // return this.modelValue;
  // },
  // },
  methods: {
    selectRating(option) {
      // console.log(option);
      // this.activeOption = option;
      this.$emit("update:modelValue", option);
  },
    },
};
</script>

<style scoped>
li {
  border: 1px gray solid;
  list-style-type: none;
  margin: 2px;
}

button {
  background: none;
  border: white;
}

.active {
  color: red;
  border: 1px red solid;
}
</style>
