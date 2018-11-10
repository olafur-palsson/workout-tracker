<template>
  <div id="app">
    <h1> Simple workout </h1>
    <img src="https://i.pinimg.com/474x/40/74/60/407460925c9e419d82b93313f0b42f71.jpg">
    <router-link :to="{ name: 'login', params: {} }">Login</router-link>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
