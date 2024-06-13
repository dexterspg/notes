<template>
  <main>
    <UserList :users="activeUsers" @list-projects="selectUser"></UserList>
    <ProjectList :user="selectedUser"></ProjectList>
  </main>
</template>

<script>
import UserList from "../../../components/demo/composition/users/UserList.vue";
import ProjectList from "../../../components/demo/composition/projects/ProjectList.vue";
import USER_DATA from "../users/dummy-data.js";
import { ref } from "vue";

export default {
  components: {
    UserList,
    ProjectList,
  },
  setup() {
    const selectedUser = ref(null);
    const activeUsers = ref(USER_DATA); //not needed to ref because USER_DATA is not chaning

    const selectUser = (uid) => {
      selectedUser.value = activeUsers.value.find((usr) => usr.id === uid);
      console.log(selectedUser.value);
    };

    return {
      selectedUser,
      activeUsers,
      selectUser,
    };
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
