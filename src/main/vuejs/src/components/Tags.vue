<template>
  <v-flex xs6>
    <v-btn @click.native="previousTag" icon small outline
           :disabled="disablePrevious"
           class="indigo--text text--darken-2 ma-1">
      <v-icon>navigate_before</v-icon>
    </v-btn>
    <tag v-for="tag in post.tags.slice(slicemin, slicemax)" :key="post.tags.id" :tag="tag"></tag>
    <v-btn @click.native="nextTag" icon small outline
           :disabled="disableNext"
           class="indigo--text text--darken-2 ma-1">
      <v-icon>navigate_next</v-icon>
    </v-btn>
  </v-flex>
</template>

<script>
  import Tag from './Tag'
  export default {
    name: 'tags',
    components: {
      Tag
    },
    props: ['post'],
    data () {
      return {
        slicemin: 0,
        slicemax: null,
        disablePrevious: null,
        disableNext: null
      }
    },
    mounted () {
      this.$nextTick(function () {
        window.addEventListener('resize', this.handleResize)
      })
      // emmit calls
      this.handleResize()
      this.isPreviousDisabled()
      this.isNextDisabled()
    },
    beforeDestroy () {
      window.removeEventListener('resize', this.handleResize)
    },
    methods: {
      nextTag: function () {
        if (this.slicemax < this.post.tags.length) {
          this.slicemin++
          this.slicemax++
          this.isPreviousDisabled()
          this.isNextDisabled()
        }
      },
      previousTag: function () {
        if (this.slicemin > 0) {
          this.slicemin--
          this.slicemax--
          this.isPreviousDisabled()
          this.isNextDisabled()
        }
      },
      isPreviousDisabled: function () {
        if (this.slicemin === 0) {
          this.disablePrevious = true
        } else if (this.post.tags.length <= this.slicemax - this.slicemin) {
          this.disablePrevious = true
        } else {
          this.disablePrevious = false
        }
      },
      isNextDisabled: function () {
        if (this.slicemax >= this.post.tags.length) {
          this.disableNext = true
        } else if (this.post.tags.length <= this.slicemax - this.slicemin) {
          this.disableNext = true
        } else {
          this.disableNext = false
        }
      },
      handleSlice: function (tagsToChange) {
        if (this.slicemax >= this.post.tags.length) {
          this.slicemin = this.slicemax - tagsToChange
        } else {
          this.slicemax = this.slicemin + tagsToChange
        }
      },
      handleResize: function () {
        if (document.documentElement.clientWidth < 500) {
          this.handleSlice(1)
        } else if (document.documentElement.clientWidth < 700) {
          this.handleSlice(2)
        } else if (document.documentElement.clientWidth < 800) {
          this.handleSlice(3)
        } else if (document.documentElement.clientWidth < 1100) {
          this.handleSlice(4)
        } else if (document.documentElement.clientWidth < 1300) {
          this.handleSlice(5)
        } else if (document.documentElement.clientWidth < 1500) {
          this.handleSlice(6)
        } else if (document.documentElement.clientWidth < 1700) {
          this.handleSlice(7)
        } else if (document.documentElement.clientWidth < 1925) {
          this.handleSlice(8)
        } else {
          this.handleSlice(9)
        }
        this.isPreviousDisabled()
        this.isNextDisabled()
      }
    }
  }
</script>

<style>

</style>
