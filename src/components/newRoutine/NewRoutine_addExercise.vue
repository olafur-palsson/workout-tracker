<!--

    Renders a form to add an exercise to the routine being created
    upon pressing Confirm it pushes the changes to the routine object
    on NewRoutine

-->
<template>
  <div class="undefined">
    <h1> {{ msg }} </h1>

    <div v-if="selectedExercise == null">
      Exercise is null
      <ul>
        <li v-for="(exercise, i) in this.$parent.allExercises" :key="i" v-on:click="selectExercise(exercise)">
          {{ exercise.name }}
        </li>
      </ul>
    </div>

    <!-- DEBUG: herna er etithvad kjaftaedi, thetta renderar ekki -->
    <div v-else>
      <div v-for="(unused, i) in setList.listOfReps" :key="i">
        Reps {{ setList.listOfReps[i] }}
        Weight {{ setList.listOfWeights[i] }}
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
      currentSetListInput: { weight: 20, reps: 8 },
      selectedExercise: null,
      setList: {
        listOfWeights: [],
        listOfReps: []
      },
      msg: 'NewRoutine_addExercise'
    }
  },
  methods: {
    goBack () {
      this.$router.push({ name: 'newRoutine' })
    },
    removeSet (index) {
      this.setList.reps.splice(index, 1)
      this.setList.weight.splice(index, 1)
    },
    selectExercise (selectedExercise) {
      this.selectedExercise = selectedExercise
    },
    sendToParent () {
      // exercise will always be the same object, so we copy to send it
      const selectedExerciseCopy = Object.assign({}, this.selectedExercise)
      // send to routine gathering
      this.$parent.addExerciseToRoutine(selectedExerciseCopy, this.setList)
      // setList will always be a new object
      this.setList = [Object.assign({}, this.currentSetListInput)]
      this.selectedExercise = { listOfReps: [], listOfWeights: [] }
      this.goBack()
    },
    addSet () {
      this.setList.listOfReps.push(this.currentSetListInput.reps)
      this.setList.listOfWeights.push(this.currentSetListInput.weight)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
