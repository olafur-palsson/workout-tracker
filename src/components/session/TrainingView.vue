<!--

    Renders the current status of the gym session
    also allows the user to check of any exercise that
    he has finished with buttons

-->
<template>
  <div class="trainingView">
    <div class="trainingView__container" v-for="(exercise, numberOfExercise) in this.exercises" :key="numberOfExercise">
      <p class="trainingView__exerciseName">{{ exercise.name }}</p>
      <div class="trainingView__set" v-for="(reps, i) in setLists[numberOfExercise].listOfReps" :key="i">
        <div class="set__item">
          <div class="icon__weight icon"></div>
          {{ setLists[numberOfExercise].listOfWeights[i] }}
        </div>
        <div class="set__item">
          <span class="alegreyasans"> Reps: </span>
          {{ setLists[numberOfExercise].listOfReps[i]    }}
        </div>
        <div class="button__container">
          <button
            v-bind:class="{
              button: true,
              button__green: setLists[numberOfExercise].finishedSets[i]
            }"
            v-on:click="toggleSet(numberOfExercise, i, setLists[numberOfExercise].finishedSets[i])">
            {{ setLists[numberOfExercise].finishedSets[i] ? '✓' : 'X' }}
          </button>
        </div>
      </div>
    </div>
    <div class="button__container">
      <button class="button" v-on:click="markAllAsDone()"> Mark all as done </button>
    </div>
    <div class="button__container">
      <button class="button button__green" v-on:click="finishRoutine()"> Finish routine </button>
    </div>
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
    async finishRoutine () {
      console.log('hello there')
      await Database.user.setActiveRoutine(-1)
      this.$parent.routine = null
      this.$router.go(-1)
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
.set__item {
  width: 3.3rem;
  display: flex;
  justify-content: space-between;
}

.trainingView {
  display: flex;
  flex-direction: column;
  max-height: 100%;
  max-width: 100%;
  margin-top: 5vh;
  width: 500px;
  overflow: auto;
  box-sizing: border-box;
}

@media screen and (max-width: 500px) {
  .trainingView {
    width: 100%;
    padding: 10px;
  }
}

.trainingView__exerciseName {
  font-size: 2rem;
  margin-bottom: 20px;
}

v-icon {
  height: 3rem;
  width: 3rem;
}

.trainingView__set {
  display: flex;
  height: 1.7rem;
  width: 100%;
  justify-content: space-around;
}

.trainingView__set .button__container {
  max-width: 30%;
}

.button.button__green {
  background-color: #73AF55;
}

.trainingView__container {
  max-width: 100%;
  width: 100%;
  background-color: #151f26;
  border: 2px solid #5BB0D8;
  border-radius: 5px;
  padding: 20px;
  box-sizing: border-box;
}

.icon.icon__weight {
  background-position: center;
  background-size: cover;
  width: 1.5rem;
  height: 1.5rem;
}
</style>
