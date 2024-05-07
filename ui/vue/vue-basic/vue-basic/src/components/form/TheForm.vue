<template>
  <form @submit.prevent="submitForm">
    <div class="form-control">
      <label for="name">Your Name </label>
      <input
        type="text"
        id="name"
        v-model.trim="name"
        @blur="validateNameInput"
      />
      <p v-if="!nameValidity">User name is invalid</p>
    </div>
    <div class="form-control">
      <label for="age">Your Age(Years) </label>
      <input type="number" id="age" v-model="age" ref="ageInput" />
    </div>
    <div>
      <h3>How did you hear about us?</h3>
      <select v-model="selectedOpt">
        <option name="wom" value="wom">Word of Mouth</option>
        <option name="people" value="ppl">People</option>
        <option name="ads" value="ads">Ads</option>
      </select>
    </div>
    <div>
      <h3>What are you interested in?</h3>
      <input type="checkbox" v-model="cb" value="news" id="news" />
      <label for="news">NEWS</label>
      <input type="checkbox" v-model="cb" value="tuts" id="tutorials" />
      <label for="tutorials">Tutorials</label>
      <input type="checkbox" v-model="cb" value="n/a" id="nothing" />
      <label for="nothing">Nothing</label>
    </div>

    <div>
      <h3>How do you learn?</h3>

      <input type="radio" v-model="learnInput" value="vid" id="video" />
      <label for="news">Video courses</label>
      <input type="radio" v-model="learnInput" value="blogs" id="blogs" />
      <label for="blogs">Blogs</label>
      <input type="radio" v-model="learnInput" value="n/a" id="other" />
      <label for="other">Other</label>
    </div>

    <RatingControl v-model="rating" />
    <div>
      <input
        type="checkbox"
        v-model="agreeterms"
        value="agreeterms"
        id="agreeterms"
      />
      <label for="agreeterms">Agree to terms of use?</label>
    </div>

    <button @click="submitForm">Submit</button>
  </form>
</template>

<script>
import RatingControl from "./RatingControl.vue";
export default {
  components: {
    RatingControl,
  },
  data() {
    return {
      name: "",
      age: null,
      selectedOpt: "ppl",
      cb: [],
      learnInput: "",
      agreeterms: false,
      rating: null,
      nameValidity: false,
    };
  },
  methods: {
    submitForm() {
      console.log(this.name);
      //type number converts v-model to number type, otherwise it is string type
      console.log(this.$refs.ageInput.value + 10);
      console.log(this.age + 10);

      console.log(this.selectedOpt);

      console.log(this.cb);
      this.cb.forEach((item) => console.log(item));
      console.log(this.learnInput);
      console.log(this.agreeterms);

      console.log(this.rating);
      this.rating = null;
    },
    validateNameInput() {
      this.nameValidity = this.name === "" ? false : true;
    },
  },
};
</script>

<style scoped></style>
