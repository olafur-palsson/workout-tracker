<!--

    Home screen, renders the outermost container of the application,
    such as the login and logout buttons and the basic routes to
    different components of the application

-->
<template>

  <div v-bind:class="backgroundClassObject">
    <div class="header">
      Simple Workout
    </div>
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
      </div>
    </div>

    <router-view/>
  </div>
</template>

<script>
import Database from '@/database/Database'
import Cookies from '@/database/Cookiehandler'

export default {
  name: 'helloThere',
  data () {
    return {
      backgroundClassObject: {
        background: true
      },
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
  created () {
    let classObject = this.backgroundClassObject
    const imageNumber = Math.floor(Math.random() * 12) + 1
    const className = 'gym' + imageNumber
    classObject[className] = true
    this.backgroundClassObject = Object.assign({}, classObject)
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

.background {
  height: 100vh;
  background-position: center;
  background-size: cover;
  justify-content: center;
}

.header {
  width: 100%;
  background-color: #151f26;
  border-bottom:  3px solid black;
  height: 10vh;
  min-height: 50px;
  position: fixed;
  font-size: 2.7rem;
  display: flex;
  align-items: center;
  justify-content: center;
}

@media screen and (max-width: 600px) {
  .header {
    font-size: 1.5rem;
  }
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

/* background images */
.gym1 {
  background-image: url('../assets/gym1.jpg')
}

.gym2 {
  background-image: url('../assets/gym2.jpg')
}

.gym3 {
  background-image: url('../assets/gym3.jpg')
}

.gym4 {
  background-image: url('../assets/gym4.jpg')
}

.gym5 {
  background-image: url('../assets/gym5.jpg')
}

.gym6 {
  background-image: url('../assets/gym6.jpg')
}

.gym7 {
  background-image: url('../assets/gym7.jpg')
}

.gym8 {
  background-image: url('../assets/gym8.jpg')
}

.gym9 {
  background-image: url('../assets/gym9.jpg')
}

.gym10 {
  background-image: url('../assets/gym10.jpg')
}

.gym11 {
  background-image: url('../assets/gym11.jpg')
}

.gym12 {
  background-image: url('../assets/gym12.jpg')
}

.logo {
  background-image: url('../assets/logo2.png');
}

</style>
