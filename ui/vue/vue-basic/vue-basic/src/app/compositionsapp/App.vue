<template>
  <TheHeader :title="notReactiveTitle" />
  <TheHeader :title="someTitle" />

  <h3>
    Objects are reactive but n ot the values inside them so need to expose
    objects
  </h3>
  <p>
    Not Reactive user passing string : {{ userNameNotReactive }} ,
    {{ ageNotReactive }}
  </p>
  <p>
    Reactive user using Ref : {{ userObjectUsingRef.name }} ,
    {{ userObjectUsingRef.age }}
  </p>
  <p>
    Reactive user using reactive : {{ userObjectUsingReactive.name }} ,
    {{ userObjectUsingReactive.age }}
  </p>
  <p>
    Reactive user using toRefs and reactive : {{ name }} ,
    {{ age }}
  </p>

  <button @click="incrementAge">Increment Age</button>

  <div>
    <p>{{ fullName }}</p>
    <div>
      <pre>Usint event listener</pre>
      <input type="text" placeholder="First Name" @input="setFirstName" />
      <input type="text" placeholder="Last Name" @input="setLastName" />
    </div>
    <div>
      <pre>using v-model</pre>
      <input type="text" placeholder="First Name" v-model="firstName" />
      <input type="text" placeholder="Last Name" v-model="lastName" />
    </div>

    <div>
      <pre>Template ref</pre>
      <input type="text" placeholder="Last Name" ref="lastNameTemplateRef" />
      <button @click="setLastNameTemplateRef">
        Set Last Name Template Ref Value
      </button>
    </div>
  </div>

  <UserData
    :firstName="firstName"
    :lastName="lastName"
    :age="age"
    @emitFullName="consoleFullName"
  />
</template>

<script>
import TheHeader from "../../components/TheHeader.vue";
import UserData from "../../components/composition/UserData.vue";
import { ref } from "vue";
import { reactive } from "vue";
import { isRef, isReactive } from "vue"; //check reactivity
import { toRefs } from "vue"; //convert to refs the value of the objects
import { computed } from "vue";
import { watch } from "vue";
import products from "./product/products.js";
import { provide } from "vue";

export default {
  components: {
    TheHeader,
    UserData,
  },
  setup() { //setup runs at the same time as lifecycle hooks beforeCreate and created so they cannot be used here
        //other hooks are still used
    let notReactiveTitle = "NonReactive Title";
    let reactiveTitle = ref("Compositions");

    setTimeout(() => {
      notReactiveTitle = "unchange Non reactive";
      reactiveTitle.value = "Reactive Title Change";
    }, 2000);

    //only works objects , use reactive
    const userObjectUsingRef = ref({
      name: "Bob",
      age: 25,
    });

    console.log("userObjectUsingRef is Reactive: " + isRef(userObjectUsingRef));
    console.log(
      "userObjectUsingRef.value.name is Reactive: " +
        isRef(userObjectUsingRef.value.name)
    );

    setTimeout(() => {
      userObjectUsingRef.value.name = "Jane";
      userObjectUsingRef.value.age = 34;
    }, 3000);

    //reactive only works for objects
    const userObjectUsingReactive = reactive({
      name: "Bob",
      age: 25,
    });

    console.log(
      "userObjectUsingReactive is Reactive: " +
        isReactive(userObjectUsingReactive)
    );
    console.log(
      "userObjectUsingReactive.name is Reactive: " +
        isReactive(userObjectUsingReactive.name)
    );

    setTimeout(() => {
      userObjectUsingReactive.name = "Jane";
      userObjectUsingReactive.age = 34;
    }, 3000);

    const user = toRefs(userObjectUsingReactive); //userObject  values (with reactive) becomes refs

    setTimeout(() => {
      user.name = "Jane";
      user.age = 34;
    }, 3000);

    const incrementAge = () => {
      userObjectUsingRef.value.age += 2;
      userObjectUsingReactive.age++;
    };

    const firstName = ref("");
    const lastName = ref("");

    const setFirstName = (event) => (firstName.value = event.target.value); //not needed when using v-model
    const setLastName = (event) => (lastName.value = event.target.value); //not needed when using v-model

    const fullName = computed(
      () => firstName.value + " " + lastName.value + " !!!!"
    );

    watch(reactiveTitle, (newValue, oldValue) => {
      console.log("Old value : " + oldValue);
      console.log("New value: " + newValue);
    });

    watch([firstName, lastName], (newValues, oldValues) => {
      console.log("Old value  (firstName ): " + oldValues[0]);
      console.log("New value  (firstName ): " + newValues[0]);
      console.log("Old value  (lastName ): " + oldValues[1]);
      console.log("New value (lastName ): " + newValues[1]);
    });

    const lastNameTemplateRef = ref(null);

    const setLastNameTemplateRef = () =>
      (lastName.value = lastNameTemplateRef.value.value);

    const consoleFullName = (fullName) =>
      console.log("Full name is " + fullName);

    provide("products", products); //imported object automatically can be use here in setup

    return {
      notReactiveTitle,
      someTitle: reactiveTitle,

      userNameNotReactive: userObjectUsingRef.value.name, //not reactive
      ageNotReactive: userObjectUsingRef.value.age, //not reactive
      userObjectUsingRef, //reactive using refs
      userObjectUsingReactive, //reactive used for objects

      name: user.name,
      age: user.age,

      incrementAge, //exposing method

      setFirstName,
      setLastName,
      fullName,

      firstName,
      lastName,

      lastNameTemplateRef,
      setLastNameTemplateRef,

      consoleFullName,
    };
  },

  methods: {
    // consoleFullName(fullName) {
    // console.log("Full name is " + fullName);
    // },
  },
};
</script>

<style scoped></style>
