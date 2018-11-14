<!--

    Renders a form to add an exercise to the routine being created
    upon pressing Confirm it pushes the changes to the routine object
    on NewRoutine

-->
<template>
  <div class="undefined">

    <div v-if="selectedExercise == null">
      <h1>Select exercise</h1>
      <div class="button__container">
        <button class="button exercise" v-for="(exercise, i) in this.$parent.allExercises" :key="i" v-on:click="selectExercise(exercise)">
          {{ exercise.name }}
        </button>
      </div>
    </div>

    <div v-else>
      <h1> {{ selectedExercise.name }} </h1>
      <h1>Reps Weight</h1>
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
      this.$router.go(-1)
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

.button.exercise {
  width: 100px;
}

</style>
