<template>
  <v-card>
    <v-card-title>
      Room to clean
      <v-spacer></v-spacer>

    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="bookings"
        :search="search"
        @click:row="editBooking"
    ></v-data-table>
    <BookingDialog
        :opened="dialogVisible"
        :booking="selectedBooking"
        @refresh="refreshList"
    ></BookingDialog>
  </v-card>
</template>

<script>
import api from "../api";

export default {
  name: "BookingListHouseKeeperList",
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
    async refreshList() {
      this.dialogVisible = false;
      this.selectedBooking = {};
      this.bookings = await api.bookings.allBookings();
    },
  },
  created() {
    this.refreshList();
    this.connectAndSubscribe();
  },
};
</script>

<style scoped></style>
