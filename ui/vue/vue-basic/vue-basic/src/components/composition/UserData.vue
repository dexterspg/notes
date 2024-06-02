<template>
  <div>
    <pre>UserData</pre>
    <div>
      <h2>Full Name using props: {{ fullName }}</h2>
      <h3>{{ age }}</h3>
      <pre>Emitting full name</pre>
      <button @click="consoleFullName">Console Fullname</button>
    </div>
    <div>
      <p> Injected products: {{ injectedProducts }}</p>
    </div>
  </div>
</template>

<script>
import { computed } from "vue";
import { inject } from "vue";
import {
    onBeforeMount,
    onMounted,
    onBeforeUpdate,
    onUpdated,
    onBeforeUnmount,
    onUnmounted
} from "vue";

export default {
  props: ["firstName", "lastName", "age"],
  setup(props, context) {
    const fullName = computed(() => props.firstName + " " + props.lastName);

    console.log(context); //context has attr , emits and slots

    const consoleFullName = () => context.emit("emitFullName", fullName.value);


    const injectedProducts = inject("products");

    onBeforeMount(()=> console.log("onBeforeMount"));
    onMounted(()=> console.log("onMounted"));
    onBeforeUpdate(()=> console.log("onBeforeUpdate"));
    onUpdated(()=> console.log("onUpdated"));
    onBeforeUnmount(()=> console.log("onBeforeUnmount"));
    onUnmounted(()=> console.log("onUnMounted"));



    return {
      fullName,
      consoleFullName,
      injectedProducts,
    };
  },
};
</script>
<style scoped></style>
