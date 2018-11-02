<template>
  <h1> Something went wrong when checking if the user was logged in. Sorry. :(</h1>
</template>

<script>

import Database from '@/components/database/Database'

export default {
  name: 'CheckLogin',
  checkRouter: () => {
    console.log(this.$router)
  },
  checkRouter2: function () {
    console.log(this.$router)
  },
  created: async function () {
    const loggedIn = await Database.isLoggedIn()
    console.log('Getting the login working here')
    console.log(loggedIn)
    if (loggedIn) {
      this.$router.push({ name: 'home' })
    } else {
      this.$router.push({ name: 'login' })
    }
  },
  data () {
    return {
      msg: 'Checking login...'
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
