<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>

        <v-toolbar color="primary" dark>
          {{ isNew ? "Create user" : "Edit user" }}
        </v-toolbar>

        <v-form >
          <v-text-field v-model="user.username" label="Username" />
          <v-text-field v-model="user.email" label="Email" />
          <v-text-field type="password" v-model="user.password" label="Password" />
          <v-text-field type="roles" v-model="user.roles" label="Role" :readonly="true" />

          <v-radio-group v-model="user.roles" :disabled="!isNew">
            <v-radio label="Admin" value="ADMIN" />
            <v-radio label="Receptionist" value="RECEPTIONIST" />
            <v-radio label="House keeper" value="HOUSEKEEPER" />
          </v-radio-group>
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

export default {
  name: "UserDialog",
  props: {
    user: Object,
    opened: Boolean,
    picked: null,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.users
            .create({
              username: this.user.username,
              email: this.user.email,
              password: this.user.password,
              roles: [this.user.roles],
            })
            .then(() => this.$emit("refresh"));

      } else {
        api.users
            .edit({
              id: this.user.id,
              username: this.user.username,
              email: this.user.email,
              password: this.user.password,
              roles: this.user.roles,
            })
      }
    },

    deletion() {
      api.users.deleteById(this.user.id)
          .then((response) => {
                if (response == true)
                  this.$emit("refresh")
              }
          );
    }
  },

  computed: {
    isNew: function () {
      console.log(this.user)
      return !this.user.id
    },
  },
};
</script>

<style scoped></style>
