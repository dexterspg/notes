<template>
  <BaseContainer>
    <BaseSearch filterType="filterProjects" />
    <div v-if="projects.length !== 0">
      <ProjectItem
        v-for="project in projects"
        :key="project.index"
        :name="project.name"
      />
    </div>
    <div v-else>
      <p>No Projects Found.</p>
    </div>
  </BaseContainer>
</template>

<script>
import ProjectItem from "./ProjectItem.vue";
import BaseSearch from "../UI/BaseSearch.vue";
import BaseContainer from "../UI/BaseContainer.vue";
import { mapGetters } from "vuex";

export default {
  components: {
    ProjectItem,
    BaseSearch,
    BaseContainer,
  },
  data() {},
  methods: {
    ...mapGetters(["projects"]),
  },
};
</script>

<style scoped></style>
