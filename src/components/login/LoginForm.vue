<!--

    LoginForm, logs the user-in using the 'Database' compenent
    Renders the LoginForm

-->
<template>
  <div class="login__form">

    <h1> Login </h1>
    <div class="form">
      <div class="input">
        <input type="text" v-model='form.email' placeholder="Email"/>
      </div>
      <div class="input">
        <input type="password" v-model='form.password' placeholder="Password"/>
      </div>
    </div>
    <div class="input">
      <button v-on:click='login(form.email, form.password)'>Log in</button>
    </div>
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

h1 {
  font-size: 2rem;
}

button {
  width: 100%;
  margin-top: 20px;
}

input {
  box-sizing: border-box;
  width: 100%;
  margin: 0;
}

.input {
  width: 100%;
  padding: 2px;
}

</style>
