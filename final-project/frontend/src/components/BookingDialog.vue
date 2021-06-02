<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create booking" : "Edit booking" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="booking.description" label="Description" />
          <v-text-field v-model="booking.tourist" label="Tourist's name" :disabled="!isNew" />
          <v-text-field v-model="booking.room" label="Reserved room" />

          <datetime format="MM-DD-YYYY H:i" width="300px" v-model="booking.checkin" label = "checkin"></datetime>
          <datetime format="MM-DD-YYYY H:i" width="300px" v-model="booking.checkout" label = "checkout"></datetime>

        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>

          <v-btn v-if="!isNew" @click="deletion">Delete</v-btn>

        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";
import datetime from 'vuejs-datetimepicker';

export default {
  components: { datetime },
  name: "BookingDialog",
  props: {
    booking: Object,
    opened: Boolean,
    tourists: {},
    tourist:Object,
    room:Object,
    checkin: Object,
    checkout: Object
  },
  data() {
    return {
      selectedTourist:{},
    };
  },
  methods: {

  persist() {
    if (this.isNew) {
      api.bookings
          .create({
            description: this.booking.description,
            tourist: this.booking.tourist,
            room: this.booking.room,
            checkin: this.booking.checkin,
            checkout: this.booking.checkout,
          })
          .then(() => this.$emit("refresh"));
    } else {
      api.bookings
          .edit({
            id: this.booking.id,
            description: this.booking.description,
            tourist: this.booking.tourist,
            room: this.booking.room,
            checkin: this.booking.checkin,
            checkout: this.booking.checkout,

          })
          .then(() => this.$emit("refresh"));
    }
  },

  deletion() {
    api.bookings.deleteById(this.booking.id)
        .then((response) => {
              if (response == true)
                this.$emit("refresh")
            }
        );
  },
    async refreshList() {
      this.selectedTourist = {};
      this.tourists = await api.tourists.allTourists();
    },

  created() {
    this.refreshList();
  },
},

computed: {
  isNew: function () {
    return !this.booking.id
  },
},
};
</script>

<style scoped></style>
