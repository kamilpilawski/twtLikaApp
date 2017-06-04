<template>
  <v-dialog width="400px" v-model="$store.state.showRegistration">
    <v-card>
      <v-card-row>
        <v-card-title>Register</v-card-title>
      </v-card-row>
      <v-alert error v-model="alertRegistration">
        {{ errorRegistration }}
      </v-alert>
      <v-card-row>
        <v-card-text>
          <v-text-field label="Email" v-model="email" @click.native="alertRegistration = false" required></v-text-field>
          <v-text-field label="Password" type="password" v-model="password" @click.native="alertRegistration = false"  required></v-text-field>
          <small>*indicates required field</small>
        </v-card-text>
      </v-card-row>
      <v-card-row actions>
        <v-btn class="blue--text darken-1" flat @click.native="changeRegistrationState">Close</v-btn>
        <v-btn class="blue--text darken-1" flat @click.native="registerUser">Save</v-btn>
      </v-card-row>
    </v-card>
  </v-dialog>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'registration',
    data () {
      return {
        email: '',
        password: '',
        alertRegistration: false,
        errorRegistration: null
      }
    },
    methods: {
      registerUser: function () {
        if (this.email !== '' && this.password !== '') {
          axios.post(this.serverIp + '/api/user/save', {
            email: this.email,
            password: this.password,
            roles: [{
              id: 3,
              title: 'user',
              description: 'default user'
            }]
          })
            .then(response => {
              console.log(response)
              this.changeRegistrationState()
            })
            .catch(error => {
              this.alertRegistration = true
              this.errorRegistration = 'Something went wrong: ' + error.response.data.message
            })
        } else {
          this.alertRegistration = true
          this.errorRegistration = 'Please fill all inputs'
        }
      },
      changeRegistrationState: function () {
        this.$store.commit('changeRegistrationState')
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
