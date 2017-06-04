<template>
  <v-toolbar fixed light>
    <v-toolbar-side-icon light
                         @click.native.stop="changeNavigationState"></v-toolbar-side-icon>
    <v-toolbar-title>{{ $store.state.title }}</v-toolbar-title>
  </v-toolbar>
</template>

<script>
  export default {
    name: 'toolbar',
    methods: {
      changeNavigationState () {
        this.$store.commit('changeNavigationState')
      }
    }
  }
</script>

<style>

</style>
