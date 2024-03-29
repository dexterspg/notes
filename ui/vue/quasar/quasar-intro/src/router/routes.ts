import { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('layouts/MainLayout/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/IndexPage.vue'),
      },
      {
        path: 'login',
        component: () => import('pages/LoginPage.vue'),
      },
      {
        path: 'two',
        component: () => import('pages/TwoPage.vue'),
      },
      {
        path: 'three',
        component: () => import('pages/ThreePage.vue'),
      },
      {
        path: 'users',
        component: () => import('pages/UsersPage.vue'),
      },
      {
        path: 'inventory',
        component: () => import('pages/InventoryPage.vue'),
      },
      {
        path: 'learnqcomponents',
        component: () => import('pages/LearnQuasarComponents/LearnQuasarComponentPage.vue'),
      },
      {
        path: 'qcard',
        component: () => import('pages/LearnQuasarComponents/QCardPage.vue'),
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
];

export default routes;
