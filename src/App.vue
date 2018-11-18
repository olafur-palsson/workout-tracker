<template>
  <div class="application">
    <div class="header" v-on:click="goHome()">
      Simple Workout
    </div>
    <div v-bind:class="backgroundClassObject">
      <div id="app">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      backgroundClassObject: {
        background: true
      }
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
    goHome () {
      this.$router.push({ name: 'home' })
    }
  }
}
</script>

<style>
* {
  text-decoration: none;
}
.heading {
  font-size: 3rem;
  margin-bottom: 20px;
}

.application {
  font-family: 'Questrial', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #e5f1f9;
  max-width: 100vw;
  height: 100%;
  width: 100%;
  box-sizing: border-box;
}

#app {
  padding-top: 10vh;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
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

.padding-top {
  padding-top: 10vh;
}

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
  cursor: pointer;
}

@media screen and (max-width: 600px) {
  .header {
    font-size: 1.5rem;
  }
}

.button__container {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
}

.button {
  width: 100%;
  margin: 3px;
  padding: 5px;
  color: #000;
  background-color: #5BB0D8;
  border: none;
  box-sizing: border-box;
  border-radius: 3px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  border: 1px solid black;

  display: flex;
  justify-content: center;
  align-items: center;
}

.button:hover {
  background-color: #fff;
  box-shadow: 0px 15px 20px rgba(255, 255, 255, 0.4);
  transform: translateY(-3px);
}

.alegreyasans {
  font-family: 'Alegreya Sans SC';
  font-weight: 700;
}

/* background images */
.gym1 {
  background-image: url('assets/gym1.jpg')
}

.gym2 {
  background-image: url('assets/gym2.jpg')
}

.gym3 {
  background-image: url('assets/gym3.jpg')
}

.gym4 {
  background-image: url('assets/gym4.jpg')
}

.gym5 {
  background-image: url('assets/gym5.jpg')
}

.gym6 {
  background-image: url('assets/gym6.jpg')
}

.gym7 {
  background-image: url('assets/gym7.jpg')
}

.gym8 {
  background-image: url('assets/gym8.jpg')
}

.gym9 {
  background-image: url('assets/gym9.jpg')
}

.gym10 {
  background-image: url('assets/gym10.jpg')
}

.gym11 {
  background-image: url('assets/gym11.jpg')
}

.gym12 {
  background-image: url('assets/gym12.jpg')
}

.logo {
  background-image: url('assets/logo2.png');
}

.icon__weight {
  background-image: url('assets/weight.png')
}

.icon__numbers {
  background-image: url('assets/numbers.png')
}
</style>
