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
          <v-text-field v-model="booking.patient" label="Tourist's name" />
          <v-text-field v-model="booking.room" label="Reserved room" />
<!--          <v-label >Patient</v-label>-->
<!--          <v-select v-model="patient"-->
<!--                    :items ="patients"-->
<!--                    item-text='name'-->
<!--                    item-value='id'-->
<!--                    :patient = "selectedPatient"-->
<!--                    >-->
<!--          </v-select>-->
<!--          <v-label >Doctor</v-label>-->
<!--          <v-select :items="doctors"-->
<!--                    @refresh="refreshList">-->
<!--          </v-select>-->

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

      // this.selectedDoctor = {};
      // this.doctors = await api.users.allDoctors();
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
