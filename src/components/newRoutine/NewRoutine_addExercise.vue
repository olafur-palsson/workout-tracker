<template>
  <div class="undefined">
    <h1> {{ msg }} </h1>

    <button v-on:click="logAllExercises()"> Print exercises </button>

    <div v-if="selectedExercise == null">
      Exercise is null
      <button v-for="(exercise, i) in this.$parent.allExercises" :key="i" v-on:click="selectExercise(exercise)">
        {{ exercise.name }}
      </button>
    </div>

    <div v-else>
      <div v-for="(set, i) in this.setList" :key="i">
        {{ set }}
        <button v-on:click="removeSet(i)"> X </button>
      </div>
      <div >
        <input v-model="currentSetListInput.reps" placeholder="reps">
        <input v-model="currentSetListInput.weight" placeholder="weight">
      </div>
      <button v-on:click="addSet()"> Add Set </button>
      <button v-on:click="sendToParent()"> Confirm </button>
    </div>

    <button v-on:click="goBack()"> Back </button>
  </div>
</template>

<script>

export default {
  // name: 'CreateRoutine_addexercise',
  data () {
    return {
      currentSetListInput: { weight: 20, reps: 12 },
      selectedExercise: null,
      setList: [],
      goBack: () => { this.$router.go(-1) },
      msg: 'NewRoutine_addExercise'
    }
  },
  methods: {
    removeSet (index) {
      this.setList.splice(index, 1)
    },
    selectExercise (selectedExercise) {
      this.selectedExercise = selectedExercise
    },
    logAllExercises () {
      console.log(this.$parent.allExercises)
    },
    sendToParent () {
      // exercise will always be the same object, so we copy to send it
      const selectedExerciseCopy = Object.assign({}, this.selectedExercise)
      // send to routine gathering
      this.$parent.addExerciseToRoutine(selectedExerciseCopy, this.setList)
      // setList will always be a new object
      this.setList = [Object.assign({}, this.currentSetListInput)]
    },
    addSet () {
      this.setList.push(Object.assign({}, this.currentSetListInput))
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
