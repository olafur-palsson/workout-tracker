<!--

Renders the overview of the routine being created,
has the button for creating the routine (saving it to database)

-->
<template>
  <div class="navigation">
    <div class="navigation__container newroutine">
      <div class="exercise__li" v-for="(exerciseAndSetList, index) in this.$parent.routine" :key="index">
        <div>
          <div>
            <p class="hint">{{ exerciseAndSetList.exercise.name }}</p>
            Avg. reps {{ averageOf(exerciseAndSetList.setList.listOfReps) }}
            - Max. weight {{ maxOf(exerciseAndSetList.setList.listOfWeights) }}
          </div>
        </div>
      </div>
      <div class="button__container">
        <button class="button" v-on:click="goToAddExcercise()">Add exercise</button>
      </div>
      <div v-if="routineHasExercise" class="button__container">
        <button class="button" v-on:click="createRoutine()">Create Routine</button>
      </div>
      {{ msg }}
    </div>
  </div>
</template>

<script>

const maxOf = array => array.reduce((max, el) => max > el ? max : el, 0)
const averageOf = array => array.reduce((sum, el) => sum + el, 0) / array.length

export default {
  name: 'NewRoutine_main',
  data () {
    return {
      props: ['getRoutine'],
      maxOf,
      averageOf,
      msg: 'Add exercise bruh',
      routineHasExercise: true
    }
  },
  created () {

  },
  methods: {
    createRoutine () {
      this.$parent.saveRoutine()
      this.$router.push({ name: 'home' })
    },
    goToAddExcercise () {
      this.$router.push({ name: 'newRoutine_addExercise' })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.newroutine.navigation__container {
  max-width: 600px;
  flex-wrap: wrap;
}

.exercise__li {
  margin-bottom: 10px;
}

.hint {
  font-size: 1.2rem;
}
</style>
