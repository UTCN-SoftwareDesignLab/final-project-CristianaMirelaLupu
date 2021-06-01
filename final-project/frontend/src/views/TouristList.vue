<template>
  <v-card>
    <v-card-title>
      Tourists
      <v-spacer></v-spacer>

      <v-btn @click="addTourist">Add Tourist</v-btn>
      <v-btn @click="goToBookings">Go to Bookings</v-btn>

    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="tourists"
        :search="search"
        @click:row="editTourist"
    ></v-data-table>
    <TouristDialog
        :opened="dialogVisible"
        :tourist="selectedTourist"
        @refresh="refreshList"
    ></TouristDialog>
  </v-card>
</template>

<script>
import api from "../api";
import TouristDialog from "../components/TouristDialog";
import router from "@/router";

export default {
  name: "TouristList",
  components: { TouristDialog },
  data() {
    return {
      tourists: [],
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Card", value: "card" },
        { text: "SSN", value: "ssn" },
        { text: "Birthdate", value: "birthDate" },
        { text: "Address", value: "address" },
      ],
      dialogVisible: false,
      selectedTourist: {},
    };
  },
  methods: {
    editTourist(tourist) {
      this.selectedTourist = tourist;
      this.dialogVisible = true;
    },
    addTourist() {
      this.dialogVisible = true;
    },

    goToBookings() {
      router.push("/bookings");
    },

    async refreshList() {
      this.dialogVisible = false;
      this.selectedTourist = {};
      this.tourists = await api.tourists.allTourists();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
