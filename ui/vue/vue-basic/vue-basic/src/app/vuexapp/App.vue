<template>
    <BaseContainer title="Vuex" #default="{count ,increment}">
        <h3>{{ count }}</h3>
        <button @click="increment">
            Add 1
        </button>

    </BaseContainer> 
</template>

<script>
import BaseContainer from "../../components/vuex/BaseContainer.vue";

export default {
  components: {
    BaseContainer,
  },
  data() {
    return {
        };
  },
};
</script>

<style scoped></style>
