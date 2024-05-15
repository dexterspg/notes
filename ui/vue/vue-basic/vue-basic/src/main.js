import { createApp } from "vue";
import App from "./app/vuexapp/App.vue";
import store from "./app/vuexapp/store/index.js";
// import router from "./app/vuexapp/router.js";
import GlobalComponent from "./components/GlobalComponent.vue";

// const unmountApp = createApp(App);

// unmountApp.mount("#unmountApp");

// setTimeout(() => {
//   console.log("unmounting #unmountApp from main.js");
//   unmountApp.unmount("#unmountApp");
// }, 1000);
//
const app = createApp(App);
app.component("GlobalComponent", GlobalComponent);

// app.use(router);
app.use(store);

// setTimeout(() => {
// console.log("mounting to #app from main.js");
app.mount("#app");
// }, 1000);
