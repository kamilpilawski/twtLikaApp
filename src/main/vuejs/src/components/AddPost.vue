<template>
  <v-flex xs8>
    <v-text-field
      name="input-1"
      label="Title"
      id="title"
      v-model="titleValue"
      maxlength="25"
    ></v-text-field>
    <v-text-field
      name="input-7-4"
      label="Dodaj wpis..."
      id="post"
      v-model="postValue"
      maxlength="141"
      multi-line
    ></v-text-field>
    <v-btn dark flat @click.native="addPost">Add</v-btn>
  </v-flex>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'add-post',
    data () {
      return {
        titleValue: '',
        postValue: ''
      }
    },
    methods: {
      addPost: function () {
        if (this.titleValue !== '' && this.postValue !== '') {
          axios.post(this.serverIp + '/api/post/save', {
            title: this.titleValue,
            content: this.postValue,
            userId: 3 // Todo fix
          })
            .then(function () {
              this.titleValue = ''
              this.postValue = ''
            })
            .catch(error => {
              console.log(error)
            })
        }
      }
    }
  }
</script>

<style>

</style>
