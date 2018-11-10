<!--

    Renders the current status of the gym session
    also allows the user to check of any exercise that
    he has finished with buttons

-->
<template>
  <div class="blank">
    {{ msg }}
    <button v-on:click="logExercises()"> Log exercises </button>
    <div v-for="(exercise, numberOfExercise) in this.exercises" :key="numberOfExercise">
      {{ exercise.name }}
      <div v-for="(reps, i) in setLists[numberOfExercise].listOfReps" :key="i">
        Weight {{ setLists[numberOfExercise].listOfWeights[i] }}
        Reps   {{ setLists[numberOfExercise].listOfReps[i]    }}
        Done   {{ setLists[numberOfExercise].finishedSets[i]  }}
        <div v-if="setLists[numberOfExercise].finishedSets[i]"> DONE :D </div>
        <button v-on:click="toggleSet(numberOfExercise, i, setLists[numberOfExercise].finishedSets[i])">
          Mark as done
        </button>
      </div>
    </div>
    <button> Mark all as done </button>
    <button> Finish routine </button>
  </div>
</template>

<script>
import Database from '@/database/Database'

export default {
  name: 'TrainingView',
  data () {
    return {
      msg: 'TrainingView',
      exercises: [],
      setLists: [],
      exercisesAndSets: null
    }
  },
  created () {
    console.log('Ding')
    this.$parent.checkIfActiveRoutine().then(() => {
      this.loadExercisesAndSets()
    })
  },
  methods: {
    logExercises () {
      console.log(this.exercise)
    },
    markAllAsDone () {
      Database.routine.markAllAsDone(this.$parent.routine.id).then(() => {
        this.loadExercisesAndSets()
      })
    },
    async loadExercisesAndSets () {
      const routineId = this.$parent.routine.id
      let setLists = await Database.routine.getAllSetListsOfRoutine(routineId)
      let exercises = await Database.routine.getAllExercisesOfRoutine(routineId)
      console.log('loading exercises')
      Promise.all([setLists, exercises]).then(array => {
        this.setLists = setLists
        this.exercises = exercises
        console.log(setLists)
        console.log('loaded')
      })
    },

    toggleSet (numberOfExercise, i, lastValue) {
      this.setLists[numberOfExercise].finishedSets[i] = !lastValue
      this.setLists = this.setLists.map(x => x)
      const setList = this.setLists[numberOfExercise]
      Database.setList.saveEntity(setList)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
