import { createRouter, createWebHistory } from "vue-router";
import TeamList from "../../components/teamusers/one/teams/TeamList.vue";
import UserList from "../../components/teamusers/one/users/UserList.vue";
import NotFound from "../../components/teamusers/one/NotFound.vue";
import TeamMembers from "../../components/teamusers/one/teams/TeamMembers.vue";
import TeamMembersProps from "../../components/teamusers/one/teams/TeamMembersProps.vue";
import TeamMembersNested from "../../components/teamusers/one/teams/TeamMembersNested.vue";
import TeamMembersNamedRoute from "../../components/teamusers/one/teams/TeamMembersNamedRoute.vue";
import UserFooter from "../../components/teamusers/one/users/UserMultipleRoutes.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      redirect: "/teams",
    },
    {
      name: "teams",
      path: "/teams",
      component: TeamList,
      // alias: "/", //alternative to redirect, path /teams not visible in url
      children: [
        {
          path: "three/:teamId",
          component: TeamMembersNested,
          props: true,
        },
        {
          name: "team-members",
          path: "four/:teamId",
          component: TeamMembersNamedRoute,
          props: true,
        },
      ],
    },
    {
      path: "/users",
      // component: UserList,
      components: {
        default: UserList,
        footer: UserFooter,
      },
    },
    {
      path: "/teams/one/:teamId",
      component: TeamMembers,
    },
    {
      path: "/teams/two/:teamId",
      component: TeamMembersProps,
      props: true,
    },

    {
      path: "/:NotFound(.*)",
      component: NotFound,
    },
  ],
  linkActiveClass: "router-link-active",
  scrollBehavior(to, from, savedPosition) {
    //savePosition is used for Back button
    console.log(to, from, savedPosition);
    if (savedPosition) {
      return savedPosition; //savePosition go to last position when we leave page and go back
    }
    return { left: 0, top: 0 };
  },
});

export default router;
