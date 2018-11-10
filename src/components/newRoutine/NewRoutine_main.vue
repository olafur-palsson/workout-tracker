<!--

    Renders the overview of the routine being created,
    has the button for creating the routine (saving it to database)

-->
<template>
  <div class="blank">
    <h1> This is blank </h1>
    {{ msg }}

    <div v-for="(exerciseAndSetList, index) in this.$parent.routine" :key="index">
      <div>
        {{ index }}
        <div> {{ exerciseAndSetList.exercise.name }},
          Avg. reps {{ averageOf(exerciseAndSetList.setList.listOfReps) }}
          Max. weight {{ maxOf(exerciseAndSetList.setList.listOfWeights) }}
        </div>
      </div>
    </div>

    <button v-on:click="goToAddExcercise()">Add exercise</button>
    <button v-on:click="createRoutine()">Create Routine</button>
    {{ msg }}
  </div>
</template>

<script>

const maxOf = array => array.reduce((max, el) => max > el ? max : el, 0)
const averageOf = array => array.reduce((sum, el) => sum + el) / array.length

export default {
  name: 'NewRoutine_main',
  data () {
    return {
      routine: [],
      props: ['getRoutine'],
      maxOf,
      averageOf,
      msg: 'Add exercise bruh'
    }
  },
  methods: {
    createRoutine () {
      this.$parent.saveRoutine()
    },
    addExerciseToRoutine (exercise, setList) {
      this.routine.push({ exercise, setList })
    },
    goToAddExcercise () {
      this.$router.push({ name: 'newRoutine_addExercise' })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
