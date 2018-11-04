<template>
  <div class="undefined">
    <h1> {{ msg }} </h1>

    <button v-on:click="logAllExercises()"> Print exercises </button>

    <div v-if="selectedExercise == null">
      Exercise is null
      <button v-for="(exercise, i) in this.$parent.allExercises" :key="i" v-on:click="selectExercise()">
        {{ exercise.name }}
      </button>
    </div>

    <div v-else>
      Exercise is NOT null
    <!--
      <div v-for="(entry, index) in this.setList">
        Reps
        <input type="number" v-bind:value="entry.reps"></input>
        Weight
        <input type="number" v-bind:value="entry.weight"></input>
        <button v-on:click="setList.push({ entry.reps, entry.weight })"> + </button>
        <button v-if="index != 0" v-on:click="setList.splice(index, 1)"> - </button>
      </div>
  -->
      <button v-on:click="sendToParent()"> Confirm </button>
    </div>

    <button v-on:click="goBack()"> Back </button>
  </div>
</template>

<script>

const sendToParent = () => {
  this.addExerciseToRoutine(this.selectedexercise, this.setList)
  this.$router.go(-1)
}

export default {
  // name: 'CreateRoutine_addexercise',
  data () {
    return {
      sendToParent: sendToParent.bind(this),
      selectedExercise: null,
      setList: [{
        weight: 20,
        reps: 12
      }],
      goBack: () => { this.$router.go(-1) },
      msg: 'NewRoutine_addExercise',
    }
  },
  methods: {
    selectExercise (selectedExercise) {
      this.selectedExercise = selectExercise
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
