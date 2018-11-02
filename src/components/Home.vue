<template>

  <div class="hello">
    <button v-on:click="logout()">Log out </button>
    <h1>{{ msg }}</h1>
    <h2>{{ msg2 }}</h2>
    <p> Sem proof of concept</p>
    <p> {{ email }}</p>
    <p> {{ password }}   :o</p>
  </div>
</template>

<script>
import Database from '@/components/database/Database'
import Cookies from '@/components/database/Cookiehandler'

export default {
  name: 'helloThere',
  data () {
    return {
      logout: () => {
        Database.logOut()
        this.$router.push({ name: 'checklogin' })
      },
      msg: 'Hello there',
      msg2: 'General Kenobi',
      email: Cookies.getByName('email'),
      password: Cookies.getByName('password')
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
