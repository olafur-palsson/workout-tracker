<!--

    Check if the user is logged in
    If logged in:
      Go to home
    Else:
      Go to login component

-->
<template>
  <h1 v-if="somethingIsWrong"> Something went wrong :( </h1>
</template>

<script>

import Database from '@/database/Database'

export default {
  name: 'CheckLogin',
  data () {
    return {
      msg: 'Checking login...',
      somethingIsWrong: false
    }
  },
  created: async function () {
    this.checkLogin()
  },
  methods: {
    async checkLogin () {
      const loggedIn = await Database.isLoggedIn()
      console.log('Login status: ' + loggedIn)
      if (loggedIn) {
        this.$router.push({ name: 'home' })
      } else {
        this.$router.push({ name: 'login' })
      }
      setTimeout(() => {
        this.somethingIsWrong = true
      }, 1000)
    }
  }
}

</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
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
