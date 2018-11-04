<template>
  <div class="newRoutine">
    <h1> This is New routine </h1>
    <router-view :allExercises="allExercises" :addExerciseToRoutine="addExerciseToRoutine"/>
  </div>
</template>

<script>
import Database from '@/database/Database'

const addExerciseToRoutine = (exerciseId, setList) => {
  const exercise = exerciseId
  this.routine.push({ exercise, setList })
}

export default {
  data () {
    return {
      allExercises: 'Not what I want',
      createRoutine: (params) => {
        console.log('Create routine not implemented @ newRoutine')
        console.log(params)
      },
      routine: [{
        exercise: {
          id: 0,
          name: 'Olafur P',
          description: 'Lol yolo'
        },
        setList: [{
          reps: 12,
          weight: 50
        }]
      }],
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
      console.log(this)
    },
    addExerciseToRoutine: addExerciseToRoutine.bind(this)
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
