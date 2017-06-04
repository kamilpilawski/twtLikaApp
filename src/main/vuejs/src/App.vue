<template>
  <v-app id="example-1">
    <navigation-menu></navigation-menu>
    <toolbar></toolbar>
    <main>
      <router-view :key="$route.fullPath"></router-view>
    </main>
    <v-footer class="indigo">
      <span>© 2017</span>
    </v-footer>
    <settings></settings>
    <registration></registration>
    <login></login>
  </v-app>
</template>

<script>
  import NavigationMenu from './components/NavigationMenu'
  import Toolbar from './components/Toolbar'
  import Settings from './components/Settings'
  import Registration from './components/Registration'
  import Login from './components/Login'
  export default {
    components: {
      Login,
      Registration,
      NavigationMenu,
      Toolbar,
      Settings},
    name: 'app'
  }
</script>

<style lang="stylus">
  @import 'stylus/main'
</style>
