<template>
  <TheHeader title="Form" />
  <TheForm />
</template>

<script>
import TheHeader from "../../components/TheHeader.vue";
import TheForm from "../../components/form/TheForm.vue";
export default {
  components: {
    TheHeader,
    TheForm,
  },
  data() {
    return {};
  },
};
</script>

<style scoped></style>
