<template>
  <base-container>
    <h2>Active Users</h2>
    <BaseSearch
      @search="updateSearch"
      :search-term="enteredSearchTerm"
    ></BaseSearch>
    <div>
      <button @click="sort('asc')" :class="{ selected: sorting === 'asc' }">
        Sort Ascending
      </button>
      <button @click="sort('desc')" :class="{ selected: sorting === 'desc' }">
        Sort Descending
      </button>
    </div>
    <ul>
      <UserItem
        v-for="user in displayedUsers"
        :key="user.id"
        :user-name="user.fullName"
        :id="user.id"
        @list-projects="$emit('list-projects', $event)"
      ></UserItem>
    </ul>
  </base-container>
</template>

<script>
import UserItem from "./UserItem.vue";
import BaseSearch from "../UI/BaseSearch.vue";
import BaseContainer from "../UI/BaseContainer.vue";
import { ref, computed, watch } from "vue";

export default {
  components: {
    UserItem,
    BaseSearch,
    BaseContainer,
  },
  props: ["users"],
  emits: ["list-projects"],
  setup(props) {
    const enteredSearchTerm = ref("");
    const activeSearchTerm = ref("");

    const availableUsers = computed(() => {
      let users = [];
      if (activeSearchTerm.value) {
        users = props.users.filter((usr) =>
          usr.fullName.includes(activeSearchTerm.value)
        );
      } else if (props.users) {
        users = props.users;
      }
      return users;
    });

    const updateSearch = (val) => {
      enteredSearchTerm.value = val;
    };

    const sorting = ref(null);

    const displayedUsers = computed(() => {
      if (!sorting.value) {
        return availableUsers.value;
      }
      return availableUsers.value.slice().sort((u1, u2) => {
        if (sorting.value === "asc" && u1.fullName > u2.fullName) {
          return 1;
        } else if (sorting.value === "asc") {
          return -1;
        } else if (sorting.value === "desc" && u1.fullName > u2.fullName) {
          return -1;
        } else {
          return 1;
        }
      });
    });

    const sort = (mode) => {
      sorting.value = mode;
    };

    watch(enteredSearchTerm, (val) => {
      setTimeout(() => {
        //add a timeout to to allow search to take time so that not every entered character the search is executed
        if (val === enteredSearchTerm.value) {
          activeSearchTerm.value = val;
        }
      }, 300);
    });

    return {
      enteredSearchTerm,
      activeSearchTerm,
      sorting,
      updateSearch,
      sort,
      displayedUsers,
      availableUsers,
    };
  },
  computed: {
    // availableUsers() {
    //   let users = [];
    //   if (this.activeSearchTerm) {
    //     users = this.users.filter((usr) =>
    //       usr.fullName.includes(this.activeSearchTerm)
    //     );
    //   } else if (this.users) {
    //     users = this.users;
    //   }
    //   return users;
    // },
    // displayedUsers() {
    //   if (!this.sorting) {
    //     return this.availableUsers;
    //   }
    //   return this.availableUsers.slice().sort((u1, u2) => {
    //     if (this.sorting === "asc" && u1.fullName > u2.fullName) {
    //       return 1;
    //     } else if (this.sorting === "asc") {
    //       return -1;
    //     } else if (this.sorting === "desc" && u1.fullName > u2.fullName) {
    //       return -1;
    //     } else {
    //       return 1;
    //     }
    //   });
    // },
  },
  // methods: {
  //   updateSearch(val) {
  //     this.enteredSearchTerm = val;
  //   },
  //   sort(mode) {
  //     this.sorting = mode;
  //   },
  // },
  watch: {
    // enteredSearchTerm(val) {
    //   // setTimeout(() => {
    //   if (val === this.enteredSearchTerm) {
    //     this.activeSearchTerm = val;
    //   }
    //   // }, 300);
    // },
  },
};
</script>

<style scoped>
ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
</style>
},
