<template>
  <v-navigation-drawer :enable-resize-watcher=true persistent v-model="$store.state.showNavigation" light>
    <v-list class="pa-0">
      <v-list-item v-if="$store.state.userName !== null">
        <v-list-tile tag="div">
          <v-list-tile-avatar>
            <v-icon>person</v-icon>
          </v-list-tile-avatar>
          <v-list-tile-content>
            <v-list-tile-title>{{ $store.state.userName }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list-item>
    </v-list>
    <v-list dense>
      <v-list-item v-if="$store.state.userName === null">
        <v-list-tile @click.native.stop="changeLoginState">
          <v-list-tile-action>
            <v-icon>person</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Login</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list-item>
      <v-list-item v-if="$store.state.userName !== null">
        <v-list-tile @click.native="logoutUser">
        <v-list-tile-action>
          <v-icon>person</v-icon>
        </v-list-tile-action>
        <v-list-tile-content>
          <v-list-tile-title>Logout</v-list-tile-title>
        </v-list-tile-content>
        </v-list-tile>
      </v-list-item>
      <v-list-item v-if="$store.state.userName === null">
        <v-list-tile @click.native.stop="changeRegistrationState">
          <v-list-tile-action>
            <v-icon>person_add</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Register</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list-item>
      <v-divider></v-divider>
      <v-list-item v-for="item in $store.getters.getMenu" :key="item">
        <v-list-tile :href="item.href">
          <v-list-tile-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>{{ item.title }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list-item>
      <v-divider></v-divider>
      <v-list-item>
        <v-list-tile @click.native.stop="changeSettingsState">
          <v-list-tile-action>
            <v-icon>settings</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Settings</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'navigation-menu',
    methods: {
      changeNavigationState: function () {
        this.$store.commit('changeNavigationState')
      },
      changeSettingsState: function () {
        this.$store.commit('changeSettingsState')
      },
      changeRegistrationState: function () {
        this.$store.commit('changeRegistrationState')
      },
      changeLoginState: function () {
        this.$store.commit('changeLoginState')
      },
      logoutUser: function () {
        axios.get(this.serverIp + '/logout').then(
          this.$store.commit('logoutUser')
        )
          .catch(error => {
            console.error(error)
          })
      }
    }
  }
  // TODO settings gdy zalogowany
</script>

<style>

</style>
