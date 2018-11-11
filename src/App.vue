<template>
  <div id="app">
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
  font-family: 'Questrial', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #e5f1f9;
  max-width: 100vw;
  width: 100%;
  box-sizing: border-box;
}

button {
  padding: 5px;
  border-radius: 3px;
  border: 1px solid black;
  background-color: #5BB0D8;
}
</style>
