<!--

Home screen, renders the outermost container of the application,
such as the login and logout buttons and the basic routes to
different components of the application

-->
<template>
  <div class="navigation">
    <div class="navigation__container">
      <router-link class="button" :to="{ name: 'newRoutine', params: {} }">
        <button> Create new routine </button>
      </router-link>

      <router-link class="button" :to="{ name: 'session', params: {} }">
        <button> Train </button>
      </router-link>

      <router-link class="button" :to="{ name: 'history', params: {} }">
        <button> History (coming soon) </button>
      </router-link>
      <div class="button logoutButton__container">
        <button class="logoutButton" v-on:click="logout()">Log out </button>
      </div>
      <h1>{{ msg }}</h1>
      <h2>{{ msg2 }}</h2>
      <GoodCheck class="checkmark" :message="'Yolo'"></GoodCheck>
    </div>
  </div>
</template>

<script>
import Database from '@/database/Database'
import Cookies from '@/database/Cookiehandler'
import GoodCheck from '@/components/checkmark-good'
import BadCheck from '@/components/checkmark-bad'

export default {
  name: 'helloThere',
  components: {
    GoodCheck,
    BadCheck
  },
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

.checkmark {
  width: 200px;
  height: 200px;
}

.button {
  width: 100%;
  padding: 5px;
}

.logoutButton__container {
  padding-top: 20px;
}

.logoutButton {
  background-color: #151F26;
  color: #E2EEF6;
}

.logoutButton:hover {
  background-color: #2d383f;
}

.navigation {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  padding-top: 10vh;
  height: 100vh;
  width: 100%;
}

.navigation__container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: 400px;
  width: 100%;
}

@media screen and (max-width: 400px) {
  .navigation__container {
    max-width: 100%;
  }
}

button {
  padding: 10px;
  background-color: #5bb0d8;
  border-radius: 3px;
  border-width: 1px;
  width: 90%;
  transition: background-color 0.2s ease-in;
}

button:hover {
  background-color: #E2EEF6;
}

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
