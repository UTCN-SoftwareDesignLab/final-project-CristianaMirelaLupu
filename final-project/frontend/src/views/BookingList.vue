<template>
  <v-card>
    <v-card-title>
      Consultations
      <v-spacer></v-spacer>

      <v-btn @click="addBooking">Add Booking</v-btn>
      <v-btn @click="goToTourists">Go to Tourists</v-btn>

    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="bookings"
        :search="search"
        @click:row="editBooking"
    ></v-data-table>
    <BookingDialog
        :opened="dialogVisible"
        :consultation="selectedBooking"
        @refresh="refreshList"
    ></BookingDialog>
  </v-card>
</template>

<script>
import api from "../api";
import BookingDialog from "../components/BookingDialog";
import router from "@/router";

export default {
  name: "BookingsList",
  components: { BookingDialog },
  data() {
    return {
      bookings: [],
      search: "",
      headers: [
        {
          text: "Description",
          align: "start",
          sortable: false,
          value: "description",
        },
        { text: "Tourist", value: "tourist" },
        { text: "Room", value: "room" },
        {text: "Checkin date", value: "checkin"},
        {text: "Checkout date", value: "checkout"},
      ],
      dialogVisible: false,
      selectedBooking: {},
    };
  },
  methods: {
    editBooking(booking) {
      this.selectedBooking = booking;
      this.dialogVisible = true;
    },
    addBooking() {
      this.dialogVisible = true;
    },

    goToTourists() {
      router.push("/tourists");
    },

    async refreshList() {
      this.dialogVisible = false;
      this.selectedBooking = {};
      this.bookings = await api.bookings.allBookings();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
