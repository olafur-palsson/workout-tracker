<template>

  <div class="hello">
    <div class="navigation">
      <router-link :to="{ name: 'newRoutine', params: {} }"> Create new routine </router-link>
      <router-link :to="{ name: 'session', params: {} }"> Go exercise </router-link>
      <router-link :to="{ name: 'history', params: {} }"> History </router-link>
    </div>

    <button v-on:click="logout()">Log out </button>
    <h1>{{ msg }}</h1>
    <h2>{{ msg2 }}</h2>

    <router-view/>

    <p> Sem proof of concept</p>
    <p> Email:  {{ email }}</p>
    <p> Password: {{ password }}   :o</p>
  </div>
</template>

<script>
import Database from '@/database/Database'
import Cookies from '@/database/Cookiehandler'

export default {
  name: 'helloThere',
  data () {
    return {
      logout: () => {
        Database.logOut()
        this.$router.push({ name: 'checkLogin' })
      },
      msg: 'Hello there',
      msg2: 'General Kenobi',
      email: Cookies.getByName('email'),
      password: Cookies.getByName('password')
    }
  },
  methods: {
    gotoSession () {
      this.$router.push({ name: 'session_selectRoutine' })
    },
    gotoHistory () {
      this.$router.push({ name: 'history' })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
