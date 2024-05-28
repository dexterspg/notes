<template>
  <TheHeader title="MixinApp" />
  <AddUser />
  <DeleteUser />
</template>

<script>
import TheHeader from "../../components/TheHeader.vue";
import AddUser from "../../components/mixin/one/AddUser.vue";
import DeleteUser from "../../components/mixin/one/DeleteUser.vue";
export default {
  components: {
    TheHeader,
    AddUser,
    DeleteUser,
  },
  data() {},
  methods: {},
};
</script>

<style scoped></style>
