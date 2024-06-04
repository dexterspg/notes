<template>
  <section>
    <div>
      <h3>{{ name }}</h3>
    </div>
    <div>
      <button @click="viewProjects">View Projects</button>
    </div>
  </section>
</template>

<script>
export default {
  props: ["id", "name"],
  data() {
    return {};
  },
  methods: {
    viewProjects() {
      this.$store.commit("filterProjects", { id: this.id });
    },
  },
};
</script>

<style scoped></style>
