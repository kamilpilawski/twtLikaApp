<template>
  <v-card class="mt-3">
    <v-card-row class="blue lighten-1">
      <v-card-title>
        <span class="white--text headline">{{ post.title }}</span>
        <v-spacer></v-spacer>
        <div>
          <!-- user email hidden on small devices - will be in list-->
          <span class="white--text hidden-xs-only">
            <v-progress-circular indeterminate class="indigo--text" v-show="!user.email"></v-progress-circular>
            <router-link class="white--text link-clear title" :to="{ name: 'User', params: { userId: this.post.userId }}">
              {{ user.email }}
            </router-link>
          </span>
          <v-menu id="marriot" bottom left origin="top right">
            <v-btn icon="icon" slot="activator" class="white--text ">
              <v-icon small>more_vert</v-icon>
            </v-btn>
            <v-list>
              <v-list-item class="hidden-sm-and-up">
                <v-list-tile>
                  <v-list-tile-title>
                    <v-progress-circular indeterminate class="indigo--text" v-show="!user.email"></v-progress-circular>
                    <router-link class="link-clear" style="color: rgba(0,0,0,0.87)" :to="{ name: 'User', params: { userId: this.post.userId }}">
                      <v-icon large>email</v-icon>
                      {{ user.email }}
                    </router-link>
                  </v-list-tile-title>
                </v-list-tile>
              </v-list-item>
              <v-list-item>
                <v-list-tile>
                  <v-list-tile-title>
                    <v-icon large>person_outline</v-icon>
                    Follow user
                  </v-list-tile-title>
                </v-list-tile>
              </v-list-item>
              <v-list-item>
                <v-list-tile>
                  <v-list-tile-title>
                    <v-icon large>person_outline</v-icon>
                    SOMETHING TODO
                  </v-list-tile-title>
                </v-list-tile>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
      </v-card-title>
    </v-card-row>
    <v-card-text>
        <v-card-row>
            <div>
              {{ post.content }}
            </div>
        </v-card-row>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-row>
      <tags v-if="post.tags.length > 0" :post="post"></tags>
      <v-spacer></v-spacer>
      <v-btn icon class="red--text" @click.native="likePost">
        <v-icon large>{{ favorite }}</v-icon>
      </v-btn>
      <!-- <v-btn flat>View Email</v-btn> -->
    </v-card-row>
  </v-card>
</template>

<script>
  import Tags from './Tags'
  import axios from 'axios'

  export default {
    name: 'wall',
    components: {
      Tags
    },
    props: ['post'],
    data () {
      return {
        user: [],
        favorite: 'favorite_outline'
      }
    },
    methods: {
      likePost: function () {
        this.favorite = 'favorite'
        // TODO Logika
      }
    },
    mounted () {
      axios.get(this.serverIp + '/api/user/id/' + this.post.userId)
        .then(response => { this.user = response.data })
        .catch(error => {
          console.log(error)
        })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
