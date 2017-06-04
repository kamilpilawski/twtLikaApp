<template>
    <v-chip label class="indigo white--text ma-1">
      <v-icon left>label_outline</v-icon>
      <router-link class="white--text link-clear" :to="{ name: 'Tag', params: { tagTitle: tag.title }}">
        {{ tag.title }}
    </router-link>
    </v-chip>
</template>

<script>
  export default {
    name: 'tag',
    props: ['tag']
  }
</script>

<style>

</style>
