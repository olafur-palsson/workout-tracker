<template>

  <div class="application">

    <div class="header">
      Simple Workout
    </div>
    <div v-bind:class="backgroundClassObject">
      <div id="app">
        <router-view class="padding-top"/>
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
  }
}
</script>

<style>
.application {
  font-family: 'Questrial', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #e5f1f9;
  max-width: 100vw;
  width: 100%;
  box-sizing: border-box;
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
}

@media screen and (max-width: 600px) {
  .header {
    font-size: 1.5rem;
  }
}

button {
  padding: 5px;
  border-radius: 3px;
  border: 1px solid black;
  background-color: #5BB0D8;
  cursor: pointer;
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
</style>
