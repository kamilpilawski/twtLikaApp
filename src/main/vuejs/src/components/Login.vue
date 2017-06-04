<template>
  <v-dialog width="400px" v-model="$store.state.showLogin">
    <v-card>
      <v-card-row>
        <v-card-title>Login</v-card-title>
      </v-card-row>
      <v-alert error v-model="alertLogin">
        {{ errorLogin }}
      </v-alert>
      <v-card-row>
        <v-card-text>
          <v-text-field label="Email" v-model="email" @click.native="alertLogin = false" required></v-text-field>
          <v-text-field label="Password" type="password" v-model="password" @click.native="alertLogin = false"
                        required></v-text-field>
          <small>*indicates required field</small>
        </v-card-text>
      </v-card-row>
      <v-card-row actions>
        <v-btn class="blue--text darken-1" flat @click.native="changeLoginState">Close</v-btn>
        <v-btn class="blue--text darken-1" flat @click.native="loginUser">Login</v-btn>
      </v-card-row>
    </v-card>
  </v-dialog>
</template>

<script>
  import axios from 'axios'
  import router from '../routes'
  export default {
    name: 'Login',
    data () {
      return {
        email: '',
        password: '',
        alertLogin: false,
        errorLogin: null
      }
    },
    methods: {
      storeLoginUser: function (username) {
        this.$store.commit('loginUser', username)
      },
      loginUser: function () {
        if (this.email !== '' && this.password !== '') {
          let formData = new FormData()
          formData.append('j_username', this.email)
          formData.append('j_password', this.password)
          axios.post(this.serverIp + '/login', formData)
            .then(response => {
              this.storeLoginUser(response.data.body.name)
              this.changeLoginState()
              router.push('/')
            })
            .catch(error => {
              this.alertLogin = true
              this.errorLogin = 'Wrong username and/or password'
              console.log(error)
            })
        } else {
          this.alertLogin = true
          this.errorLogin = 'Please fill all inputs'
        }
      },
      changeLoginState: function () {
        this.$store.commit('changeLoginState')
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
