import { createApp } from "vue";
import App from "./app/teamusersapp/App.vue";
import router from "./app/teamusersapp/routes.js";
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

app.use(router);

// setTimeout(() => {
// console.log("mounting to #app from main.js");
app.mount("#app");
// }, 1000);
