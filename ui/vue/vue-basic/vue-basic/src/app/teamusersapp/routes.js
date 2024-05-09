import { createRouter, createWebHistory } from "vue-router";
import TeamList from "../../components/teamusers/one/teams/TeamList.vue";
import UserList from "../../components/teamusers/one/users/UserList.vue";
import TeamMembers from "../../components/teamusers/one/teams/TeamMembers.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/teams",
      component: TeamList,
    },
    {
      path: "/users",
      component: UserList,
    },
    {
      path: "/teams/:teamId",
      component: TeamMembers,
    },
  ],
  linkActiveClass: "router-link-active",
});

export default router;
