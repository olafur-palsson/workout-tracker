<!--

Blank compenent to use as a template when creating new components
(it's a basic 'hello world' compenent that has Blankynes instead of
hello world)

-->
<template>
  <div class="routineView">
    <h1 class="routine__name"> {{ routine.name }} </h1>
    <div class="exercise" v-for="(unused, i) in routine.exercises" :key="i">
      <div class="exercise__info">
        <div class="safe__buttons">
          <p class="exercise__name">{{ routine.exercises[i].name }}</p>
          <div class="button__container">
            <button class="button" v-on:click="selectExercise(i)" > Show details / Edit </button>
          </div>
        </div>
        <div class="button__container red">
          <button class="button button__red" v-on:click="removeExercise(i)"> X </button>
        </div>
      </div>
      <div v-if="selectedExercise == i" class="routineView__set" v-for="(unused, j) in routine.setLists[i].listOfWeights" :key="j">
        <div class="set__item">
          <div class="icon__weight icon"></div>
          {{ routine.setLists[i].listOfWeights[j] }}
        </div>
        <div class="set__item">
          <span class="alegreyasans"> Reps: </span>
          {{ routine.setLists[i].listOfReps[j] }}
        </div>
        <div class="button__container">
          <button class="button button__red" v-on:click="removeSet(i, j)"> X </button>
        </div>
      </div>
      <!-- button v-if="selectedExercise == i"> Add/edit set (not implementad)</button -->
    </div>
    <div class="button__container addExercise__button">
      <button class="button" v-on:click="show_addExercise()"> Add Exercise </button>
    </div>
    <div v-if="addingExercise">
      <AddExercise/>
    </div>
    <div class="button__container">
      <button class="button" v-on:click="updateRoutine()"> Confirm changes </button>
    </div>
  </div>

</template>

<script>
import Database from '@/database/Database'
import AddExercise from '@/components/AddExercise'

export default {
  name: 'RoutineView',
  components: {
    AddExercise
  },
  data () {
    return {
      msg: 'RoutineView',
      routineFrame: this.$parent.routine,
      routine: null,
      addingExercise: false,
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
      this.addingExercise = false
    },
    async loadExercises () {
      const allExercises = await Database.exercise.getAll()
      this.allExercises =  allExercises
    },
    selectExercise (index) {
      console.log(index)
      this.selectedExercise = index === this.selectedExercise ? null : index
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
    show_addExercise () {
      console.log('whit')
      this.addingExercise = true
    },
    async updateRoutine () {
      let exerciseIds = this.routine.exercises.map(exercise => exercise.id)
      console.log(this.routine.setLists)
      let setListsIds = await Database.objectToIds(this.routine.setLists, setList => {
        return Database.setList.saveEntity(setList)
      })
      await Database.routine.saveEntity(this.routine.name, exerciseIds, setListsIds, this.routine.id)
    }
  }
}
</script>

<style scoped>
.set__item {
  width: 3.3rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.routineView__set {
  display: flex;
  height: 1.8rem;
  width: 500px;
  justify-content: space-around;
}

.routineView__set .button__container {
  max-width: 20%;
}

.icon.icon__weight {
  background-position: center;
  background-size: cover;
  width: 1.5rem;
  height: 1.5rem;
}

.routineView {
  width: 100%;
  max-width: 600px;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 10%;
  max-height: 100%;
}

.routine__name {
  font-size: 2.5rem;
  margin-bottom: 20px;
}

.exercise {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
}

.exercise__info {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.exercise__name {
  min-width: 25%;
}

.safe__buttons {
  display: flex;
  align-items: center;
  max-width: 75%;
  width: 100%;
}

.addExercise__button {
  padding-top: 20px;
}

.button__container.red {
  max-width: 2rem;
  align-self: flex-end;
}

.button__red {
  background-color: #D06079;
}
</style>
