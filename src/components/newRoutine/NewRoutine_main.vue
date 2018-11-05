
<template>
  <div class="blank">
    <h1> This is blank </h1>
    {{ msg }}
    <!--
    <div>
      <ol>
        <li> {{ this.$parent.routine}},
        </li>
      </ol>
    </div>
    -->

    <div v-for="(exerciseAndSetList, index) in this.$parent.routine" :key="index">
      <ol>
        <li> {{ exerciseAndSetList.exercise.name }},
          Avg. reps {{ averageOfArrayOfObjectsByKey(exerciseAndSetList.setList, 'reps') }}
          Max. weight {{ maxOfArrayOfObjectsByKey(exerciseAndSetList.setList, 'weight')}}
        </li>
      </ol>
    </div>

    <button v-on:click="goToAddExcercise()">Add exercise</button>
    <button v-on:click="createRoutine(form)">Create Routine</button>
    <button v-on:click="logRoutine()">Check Routine</button>
    {{ msg }}
  </div>
</template>

<script>

const maxOfArrayOfObjectsByKey = (arrayOfObjects, key) => {
  return arrayOfObjects.reduce((max, el) => max > el[key] ? max : el[key], 0)
}

const averageOfArrayOfObjectsByKey = (arrayOfObjects, key) => {
  return arrayOfObjects.reduce((sum, el) => sum + el[key], 0) / arrayOfObjects.length
}

export default {
  name: 'NewRoutine_main',
  data () {
    return {
      routine: [],
      props: ['getRoutine'],
      averageOfArrayOfObjectsByKey,
      maxOfArrayOfObjectsByKey,
      msg: 'Add exercise bruh'
    }
  },
  methods: {
    addExerciseToRoutine (exercise, setList) {
      console.log(this)
      this.routine.push({ exercise, setList })
    },
    logRoutine () {
      console.log(this.$parent.routine)
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
