import Vue from "vue";
import VueRouter from "vue-router";
import UserList from "../views/UserList.vue";
import BookingList from "../views/BookingList.vue";
import TouristList from "@/views/TouristList";
import BookingListHousekeeper from "@/views/BookingListHousekeeper";
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
    name: "Bookings",
    component: BookingList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isReceptionist) {
        next();
      } else {
        next({ name: "Booking" });
      }
    },
  },

  {
    path: "/housekeeperbookings",
    name: "to clean",
    component: BookingListHousekeeper,
    beforeEnter: (to, from, next) => {
      if (store.getters.isHousekeeper) {
        next();
      } else {
        next({ name: "to clean" });
      }
   },
  },
];

const router = new VueRouter({
  routes,
});

export default router;
