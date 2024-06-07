<template>
  <main>
    <UserList :users="activeUsers" @list-projects="selectUser"></UserList>
    <ProjectList :user="selectedUser"></ProjectList>
  </main>
</template>

<script>
import UserList from "../../../components/demo/options/users/UserList.vue";
import ProjectList from "../../../components/demo/options/projects/ProjectList.vue";
import USER_DATA from "../users/dummy-data.js";

export default {
  components: {
    UserList,
    ProjectList,
  },
  data() {
    return {
      selectedUser: null,
      activeUsers: USER_DATA,
    };
  },
  methods: {
    selectUser(uid) {
      this.selectedUser = this.activeUsers.find((usr) => usr.id === uid);
    },
  },
};
</script>
<style>
* {
  box-sizing: border-box;
}
html {
  font-family: sans-serif;
}
body {
  margin: 0;
}

main {
  display: flex;
  justify-content: space-around;
}

button {
  font: inherit;
  border: 1px solid #00006b;
  background-color: transparent;
  color: #00006b;
  padding: 0.5rem 1.5rem;
  cursor: pointer;
  margin: 0.5rem 0.5rem 0.5rem 0;
}
button:hover,
button:active {
  background-color: #efefff;
}

button.selected {
  background-color: #00006b;
  color: white;
}
</style>
