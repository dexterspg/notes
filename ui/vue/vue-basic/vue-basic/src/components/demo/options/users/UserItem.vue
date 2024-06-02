<template>
  <section>
    <div>
      <h3>{{ name }}</h3>
    </div>
    <div>
      <button>View Projects</button>
    </div>
  </section>
</template>

<script>
export default {
  props: ["name"],
  data() {
    return {};
  },
};
</script>

<style scoped></style>
