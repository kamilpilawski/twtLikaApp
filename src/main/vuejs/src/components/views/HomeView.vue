<template>
  <v-container fluid>
    <v-alert error dismissible v-model="alert">
      {{ error }}
    </v-alert>
    <add-post></add-post>
    <wall v-for="post in posts" :key="post.id" :post="post"></wall>
  </v-container>
</template>

<script>
  import Wall from '../Wall'
  import AddPost from '../AddPost'
  import axios from 'axios'

  export default {
    name: 'home-view',
    components: {
      Wall, AddPost
    },
    data () {
      return {
        error: 'Please be sure you are logged in! Something went wrong - check console',
        alert: false,
        info: false,
        posts: [],
        request: '',
        right: null
      }
    },
    props: ['viewName'],
    computed: {
      computedUsername: function () {
        return this.$store.getters.getUserName
      }
    },
    watch: {
      computedUsername: function (computedUsername) {
        this.fetchData()
      }
    },
    methods: {
      changeTitle: function (newTitle) {
        this.$store.commit('changeTitle', newTitle)
      },
      fetchData: function () {
        if (this.viewName === 'home') {
          this.request = '/api/post/liked'
          this.changeTitle('Home')
          // TODO aktualnie zalogowany
          // TODO Obsługa braku wpisów
        } else if (this.viewName === 'user') {
          this.request = '/api/post/user/' + this.$route.params.userId

          axios.get(this.serverIp + '/api/user/id/' + this.$route.params.userId).then(response => { this.changeTitle(response.data.email) })
            .catch(error => {
              console.log(error)
            })
        } else if (this.viewName === 'tag') {
          this.request = '/api/post/tag/title/' + this.$route.params.tagTitle
          this.changeTitle('#' + this.$route.params.tagTitle)
        } else if (this.viewName === 'like') {
          this.request = '/api/post/liked'
          this.changeTitle('Favorite')
        } else {
          this.request = '/api/post/liked'
        }
        axios.get(this.serverIp + this.request)
        // TODO Obsługa braku wpisów
          .then(response => {
            this.posts = response.data
          })
          .catch(error => {
            console.error(error)
            this.alert = true
          })
      }
    },
    mounted () {
      this.fetchData()
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
