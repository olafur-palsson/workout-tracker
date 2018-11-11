<!--

    Not implemented yet

-->
<template>
  <div class="header">
    Simple Workout
  </div>
  <div class="blank">
    <h1> This is blank </h1>
    {{ msg }}
  </div>
</template>

<script>
export default {
  name: 'ExerciseView',
  data () {
    return {
      msg: 'ExerciseView'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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
</style>
