
<template>
  <div class="login">

    <h1> This is loginform </h1>
    <input type="text" v-model='form.email' placeholder="Email"/>
    <input type="password" v-model='form.password' placeholder="Password"/>
    <button v-on:click='login(form.email, form.password)'>Log in</button>
    {{ msg }}
    <router-link :to="{ name: 'signupForm', params: {} }">Don't have an account?</router-link>
  </div>
</template>

<script>

import Database from '@/database/Database'

export default {
  data () {
    return {
      login: (email, password) => {
        Database.logIn(email, password)
        this.$router.push({ name: 'checkLogin' })
      },
      form: {
        email: '',
        password: ''
      },
      msg: 'This is something'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
