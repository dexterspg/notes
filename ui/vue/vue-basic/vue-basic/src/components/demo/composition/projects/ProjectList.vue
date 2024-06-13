<template>
  <BaseContainer v-if="user">
    <h2>{{ user.fullName }}: Projects</h2>
    <BaseSearch
      v-if="hasProjects"
      @search="updateSearch"
      :search-term="enteredSearchTerm"
    ></BaseSearch>
    <ul v-if="hasProjects">
      <ProjectItem
        v-for="prj in availableProjects"
        :key="prj.id"
        :title="prj.title"
      ></ProjectItem>
    </ul>
    <h3 v-else>No projects found.</h3>
  </BaseContainer>
  <BaseContainer v-else>
    <h3>No user selected.</h3>
  </BaseContainer>
</template>

<script>
import ProjectItem from "./ProjectItem.vue";
import BaseSearch from "../UI/BaseSearch.vue";
import BaseContainer from "../UI/BaseContainer.vue";
import { ref, computed, watch, toRefs } from "vue";

export default {
  components: {
    ProjectItem,
    BaseSearch,
    BaseContainer,
  },
  props: ["user"],
  setup(props) {
    const enteredSearchTerm = ref("");
    const activeSearchTerm = ref("");

    const updateSearch = (val) => (enteredSearchTerm.value = val);

    const availableProjects = computed(() => {
      if (activeSearchTerm.value) {
        return props.user.projects.filter((prj) =>
          prj.title.includes(activeSearchTerm.value)
        );
      }
      return props.user.projects;
    });

    const hasProjects = computed(() => {
      return props.user.projects && availableProjects.value.length > 0;
    });

    watch(enteredSearchTerm, (val) => {
      setTimeout(() => {
        if (val === enteredSearchTerm.value) {
          activeSearchTerm.value = val;
        }
      }, 300);
    });

    // watch(props.user, () => {// not going to work because user is not reactive
    // watch(props, () => {// will watch all props, not recommended
    //solution : make user reactive

    //alternative but destructuring is more modern way
    // const propsWithRefs = toRefs(props);
    // const user = propsWithRefs.user;

    const { user } = toRefs(props);

    watch(user, () => {
      enteredSearchTerm.value = "";
      console.log("entered search term for project set to blank");
    });

    return {
      enteredSearchTerm,
      updateSearch,
      availableProjects,
      hasProjects,
    };
  },
};
</script>

<style scoped></style>
