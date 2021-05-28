<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>

        <v-toolbar color="primary" dark>
          {{ isNew ? "Create tourist" : "Edit tourist" }}
        </v-toolbar>

        <v-form >
          <v-text-field v-model="tourist.name" label="Name" />
          <v-text-field v-model="tourist.card" label="Credit number" />
          <v-text-field v-model="tourist.ssn" label="SSN" />
          <v-text-field type="date" v-model="tourist.birthDate" label="Birthdate" />
          <v-text-field v-model="tourist.address" label="Address" />

        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "TouristDialog",
  props: {
    tourist: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.tourists
            .create({
              name: this.tourist.name,
              card: this.tourist.card,
              ssn: this.tourist.ssn,
              birthDate: this.tourist.birthDate,
              address: this.tourist.address
            })
            .then(() => this.$emit("refresh"));

      } else {
        api.tourists
            .edit({
              id: this.tourist.id,
              name: this.tourist.name,
              card: this.tourist.card,
              ssn: this.tourist.ssn,
              birthDate: this.tourist.birthDate,
              address: this.tourist.address
            })
      }
    },
  },

  computed: {
    isNew: function () {
      console.log(this.tourist)
      return !this.tourist.id
    },
  },
};
</script>

<style scoped></style>
