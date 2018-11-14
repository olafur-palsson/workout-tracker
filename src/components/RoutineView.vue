<!--

Blank compenent to use as a template when creating new components
(it's a basic 'hello world' compenent that has Blankynes instead of
hello world)

-->
<template>
  <div class="routineView">
    <h1> RoutineView </h1>
    <br/>
    <br/>
    <br/>
    <div class="exercise" v-for="(unused, i) in routine.exercises" :key="i">
      {{ routine.exercises[i].name }}
      <div class="button__container">
        <button class="button" v-on:click="selectExercise(i)" > Show details / Edit </button>
        <button v-on:click="removeExercise(i)"> X </button>
      </div>
      <div v-if="selectedExercise == i" class="set" v-for="(unused, j) in routine.setLists[i].listOfWeights" :key="j">
        {{ routine.setLists[i].listOfWeights[j] }}
        {{ routine.setLists[i].listOfReps[j] }}
        <button v-on:click="removeSet(i, j)"> X </button>
      </div>
      <button v-if="selectedExercise == i"> Add/edit set (not implementad)</button>
    </div>
    <button v-on:click="gotoAddExercise()"> Add Exercise </button>
    <router-view/>
  </div>
</template>

<script>
import Database from '@/database/Database'

export default {
  name: 'RoutineView',
  data () {
    return {
      msg: 'RoutineView',
      routineFrame: this.$parent.routine,
      routine: null,
      selectedExercise: null,
      allExercises: null,
      notAddingExercise: true,
      needsUpdate: false
    }
  },
  created () {
    this.loadExercises()
    this.load()
  },
  methods: {
    async addExerciseToRoutine (exercise, setList) {
      this.routine.exercises.push(exercise)
      this.routine.setLists.push(setList)
      this.notAddingExercise = false
    },
    async loadExercises () {
      const allExercises = await Database.exercise.getAll()
      this.allExercises =  allExercises
    },
    selectExercise (index) {
      console.log(index)
      this.selectedExercise = index
    },
    async load () {
      const exercises = await Database.routine.getAllExercisesOfRoutine(this.routineFrame.id)
      const setLists = await Database.routine.getAllSetListsOfRoutine(this.routineFrame.id)
      this.routine = Object.assign(this.routineFrame, { exercises, setLists })
    },
    removeExercise (index) {
      this.routine.setLists.splice(index, 1)
      this.routine.exercises.splice(index, 1)
      Database.routine.removeExerciseFromRoutine(this.routine.id, index)
      this.routine = Object.assign({}, this.routine)
    },
    // Sorry, this is very ugly
    removeSet (exerciseIndex, setIndex) {
      let setList = this.routine.setLists[exerciseIndex]
      setList.listOfWeights.splice(setIndex, 1)
      setList.listOfReps.splice(setIndex, 1)
      setList.finishedSets.splice(setIndex, 1)
      Database.setList.saveEntity(setList)
      this.routine.setLists = this.routine.setLists
      this.selectedExercise = -1
      this.selectedExercise = exerciseIndex
    },
    addExercise (exercise, setList) {
      this.routine.setLists.push(setList)
      this.routine.exercises.push(exercise)
    },
    gotoAddExercise () {
      this.notAddingExercise = false
      this.$router.push({ name: 'session_routine_addExercise' })
    },
    async updateRoutine () {
      let exerciseIds = this.routine.exercises.map(exercise => exercise.id)
      let setListsIds = await Database.objectToIds(this.routine.setLists, setList => {
        return Database.setList.saveEntity(setList)
      })
      // BUG: routineId er ekki latid fylgja med
      await Database.routine.saveEntity(exerciseIds, setListsIds)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
