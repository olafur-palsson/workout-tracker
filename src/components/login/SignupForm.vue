
<template>
  <div class="login">

    <h1> This is signupform </h1>
    <input type="text" placeholder="Username" v-model="form.user"/>
    <input type="text" placeholder="Email" v-model="form.email"/>
    <input type="password" placeholder="Password" v-model="form.password" />
    <button v-on:click="signup(form.user, form.email, form.password)">Sign up</button>

    {{ msg }}
    <router-link :to="{ name: 'login', params: {} }">Already have an account?</router-link>
  </div>
</template>

<script>
import Database from '@/database/Database'

export default {
  name: 'signup',
  data () {
    return {
      form: {
        email: '',
        user: '',
        password: ''
      },
      signup: (username, email, password) => {
        Database.user.newUser(username, email, password)
        Database.logIn(email, password)
        this.$router.push({ name: 'checkLogin' })
      },
      msg: 'This is something'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
