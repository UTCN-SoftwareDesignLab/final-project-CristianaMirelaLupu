import Vue from "vue";
import VueRouter from "vue-router";
import UserList from "../views/UserList.vue";
import BookingList from "../views/BookingList.vue";
import TouristList from "@/views/TouristList";
import { auth as store } from "../store/auth.module";
import Login from "../views/Login";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/users",
    name: "Users",
    component: UserList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
      } else {
        next({ name: "User" });
      }
    },
  },
  {
    path: "/tourists",
    name: "Tourists",
    component: TouristList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isReceptionist) {
        next();
      } else {
        next({ name: "Tourist" });
      }
    },
  },
  {
    path: "/bookings",
    name: "Consultations",
    component: BookingList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isSecretary) {
        next();
      } else {
        next({ name: "Booking" });
      }
   },
  },
];

const router = new VueRouter({
  routes,
});

export default router;
