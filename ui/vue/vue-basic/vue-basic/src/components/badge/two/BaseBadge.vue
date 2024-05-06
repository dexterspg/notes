<template>
  <p v-if="role === 'admin'" class="admin">
    {{ caption }}
  </p>
  <p v-if="role === 'author'" class="author">
    {{ caption }}
  </p>
</template>

<script>
export default {
  props: ["role", "caption"],
  data() {
    return {};
  },
};
</script>

<style scoped>
.admin {
  color: red;
}

.author {
  color: blue;
}
</style>
