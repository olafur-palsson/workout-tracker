<!--

    SignupForm, uses the information provided to sign the user in
    then logs the user in and does a check just in case to make
    sure the user has access to relevant data in the database

-->
<template>
  <div class="login__form">

    <h1> This is signupform </h1>
    <div class="form">
      <div class="input">
        <input type="text" placeholder="Username" v-model="form.user"/>
      </div>

      <div class="input">
        <input type="text" placeholder="Email" v-model="form.email"/>
      </div>

      <div class="input">
        <input type="password" placeholder="Password" v-model="form.password" />
      </div>

      <div class="button__container">
        <button class="button" v-on:click="signup(form.user, form.email, form.password)">Sign up</button>
      </div>
    </div>
    <a class="pointer" v-on:click="toLoginForm()"> Already have an account? </a>
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
      }
    }
  },
  methods: {
    async signup (username, email, password) {
      await Database.user.newUser(username, email, password)
      Database.logIn(email, password)
      this.$router.push({ name: 'checkLogin' })
    },
    toLoginForm () {
      this.$parent.transition('login')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

h1 {
  font-size: 2rem;
  margin-bottom: 20px;
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

.pointer {
  cursor: pointer;
}
</style>
