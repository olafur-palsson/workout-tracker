
<template>
  <div class="blank">
    {{ msg }}
    <div v-for="(exerciseAndSet, exerciseIndex) in this.exercisesAndSets" :key="exerciseIndex">
      {{ exerciseAndSet.exercise.name }}
      <div v-for="(unused, setIndex) in exerciseAndSet.setList.listOfReps" :key="setIndex">
        Weight {{ exerciseAndSet.setList.listOfWeights[setIndex] }}
        Reps {{ exerciseAndSet.setList.listOfReps[setIndex] }}
        Done {{ exerciseAndSet.setList.finishedSets[setIndex] }}
        <div v-if="exerciseAndSet.setList.finishedSets[setIndex]"> DONE :D </div>
        <button v-on:click="toggleSet(exerciseIndex, setIndex, exerciseAndSet.setList.finishedSets[setIndex])">
          Mark as done
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import Database from '@/database/Database'

export default {
  name: 'RoutineView',
  data () {
    return {
      msg: 'RoutineView',
      exercisesAndSets: null
    }
  },
  created () {
    this.loadExercisesAndSets()
  },
  methods: {
    async loadExercisesAndSets () {
      let exercises = Database.idsToObjects(this.$parent.routine.exerciseIds, id => {
        return Database.exercise.getOne(id)
      })
      let setLists = Database.idsToObjects(this.$parent.routine.setListIds, id => {
        return Database.setList.getOne(id)
      })
      exercises = await exercises
      setLists = await setLists
      this.exercisesAndSets = exercises.map((unused, i) => {
        return {
          exercise: exercises[i],
          setList: setLists[i]
        }
      })
    },
    toggleSet (exerciseIndex, setIndex, lastValue) {
      this.exercisesAndSets[exerciseIndex].setList.finishedSets[setIndex] = !lastValue
      this.exercisesAndSets = Object.assign({}, this.exercisesAndSets)
      const setList = this.exercisesAndSets[exerciseIndex].setList
      Database.setList.saveEntity(setList)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
