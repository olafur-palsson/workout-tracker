<!--

    Container for the Routine creation process, keeps the routine and
    handles the saving of the routine to database

-->
<template>
  <div class="newRoutine">
    <h1> This is New routine </h1>
    <router-view :allExercises="allExercises" />
  </div>
</template>

<script>
import Database from '@/database/Database'

export default {
  data () {
    return {
      allExercises: 'Not what I want',
      routine: [],
      msg: 'This is something'
    }
  },
  created () {
    this.loadExercises()
  },
  methods: {
    async loadExercises () {
      const allExercises = await Database.exercise.getAll()
      this.allExercises =  allExercises
    },
    addExerciseToRoutine (exerciseId, setList) {
      const exercise = exerciseId
      this.routine.push({ exercise, setList })
    },
    async saveRoutine () {
      let exerciseIds = this.routine.map(exerciseAndSetList => exerciseAndSetList.exercise.id)
      let setListsIds = await Database.objectToIds(this.routine, exerciseAndSetList => {
        return Database.setList.saveEntity(exerciseAndSetList.setList)
      })
      let routineId = await Database.routine.saveEntity(exerciseIds, setListsIds)
      await Database.user.addRoutineToUser(routineId)
    }
  }
}

/* Steypa, en madur tharf ad redda svona ut ur einhverju
easily filled formi
routine: [
{
exercise: object,
setlist: [
{
reps: 12,
weight: 50
},
{...},
...,
{...}
]
},
{...},
...
{...},
],
*/

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
