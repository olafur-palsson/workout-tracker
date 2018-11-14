<!--

Home screen, renders the outermost container of the application,
such as the login and logout buttons and the basic routes to
different components of the application

-->
<template>
  <div class="navigation">
    <div class="navigation__container">
      <router-link class="button__container" :to="{ name: 'newRoutine', params: {} }">
        <button class="button"> Create new routine </button>
      </router-link>

      <router-link class="button__container" :to="{ name: 'session', params: {} }">
        <button class="button"> Routines </button>
      </router-link>

      <router-link class="button__container" :to="{ name: 'history', params: {} }">
        <button class="button"> History (coming soon) </button>
      </router-link>
      <div class="button__container logoutButton__container">
        <button class="button logoutButton" v-on:click="logout()">Log out </button>
      </div>
      <h1>{{ msg }}</h1>
      <h2>{{ msg2 }}</h2>
      <div v-if="displayCheckmark" v-bind:class="{ checkmark__box: true, transparent: checkmarkTransparent }">
        <GoodCheck class="checkmark" :size="100" :message="'Yolo'"></GoodCheck>
      </div>
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
      password: Cookies.getByName('password'),
      displayCheckmark: true,
      checkmarkTransparent: false
    }
  },

  methods: {
    gotoSession () {
      this.$router.push({ name: 'session_selectRoutine' })
    },
    gotoHistory () {
      this.$router.push({ name: 'history' })
    }
  },
  created () {
    setTimeout(() => {
      this.checkmarkTransparent = true
      setTimeout(() => {
        this.displayCheckmark = false
      }, 3000)
    }, 1000)
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.checkmark__box.transparent {
  background-color: rgba(0, 0, 0, 0);
}

.checkmark__box {
  width: 100vw;
  height: 110vh;
  top: -20px;
  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
  pointer-events: none;
  background-color: rgba(0, 0, 0, 0.6);
  transition: background-color 0.5s;
}

.logoutButton__container {
  padding-top: 20px;
}

.logoutButton {
  background-color: #151F26;
  color: #E2EEF6;
}

.logoutButton:hover {
  background-color: #fff;
  box-shadow: 0px 15px 20px rgba(255, 255, 255, 0.4);
  transform: translateY(-3px);
  color: #000;
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
