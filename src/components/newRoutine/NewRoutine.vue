<!--

    Container for the Routine creation process, keeps the routine and
    handles the saving of the routine to database

-->
<template>
  <div class="newRoutine">
    <router-view v-if="routineName" :allExercises="allExercises" />
    <div class="routineNameInput" v-else>
      <div class="routineInputContainer">
        <input class="input routine__name" v-model="routineNameInput" placeholder="Pick a name">
      </div>
      <div class="button__container">
        <button class="button" v-on:click="setName()"> OK </button>
      </div>
    </div>
  </div>
</template>

<script>
import Database from '@/database/Database'

export default {
  data () {
    return {
      allExercises: 'Not what I want',
      routineName: null,
      routineNameInput: null,
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
    setName () {
      if (this.routineNameInput === '') return
      this.routineName = this.routineNameInput
    },
    addExerciseToRoutine (exercise, setList) {
      this.routine.push({ exercise, setList })
      this.$router.back()
    },
    async saveRoutine () {
      let exerciseIds = this.routine.map(exerciseAndSetList => exerciseAndSetList.exercise.id)
      let setListsIds = await Database.objectToIds(this.routine, exerciseAndSetList => {
        return Database.setList.saveEntity(exerciseAndSetList.setList)
      })
      let routineId = await Database.routine.saveEntity(this.routineName, exerciseIds, setListsIds)
      await Database.user.addRoutineToUser(routineId)
    }
    /*
    async saveRoutine () {
      console.log(this.routine[0])
      let exerciseIds = this.routine.exercises.map(exercise => exercise.id)
      console.log(this.routine.setLists)
      let setListsIds = await Database.objectToIds(this.routine.setLists, setList => {
        return Database.setList.saveEntity(setList)
      })
      let routineId
      if (this.routine.id) routineId = this.routine.id
      await Database.routine.saveEntity(this.routine.name, exerciseIds, setListsIds, this.routine.id)
    }
    */
  }
}
</script>

<style scoped>
.routineInputContainer {
  width: 100%;
}

.routineNameInput {
  height: 100%;
  display: flex;
  align-items: center;
  flex-direction: column;
  padding: 10px;
}

.newRoutine {
  max-width: 100%;
  width: 400px;
  height: 100%;
  max-height: 100%;
}

.input.routine__name {
  background-color: rgba(0, 0, 0, 0.3);
  font-size: 4rem;
  max-width: 100%;
  margin-top: 30vh;
  border-width: 0;
  color: #CEDAE2;
  display: flex;
  justify-content: center;
  border-radius: 6px;
  margin-bottom: 20px;
  text-align: center;
}

@media screen and (max-width: 400px) {
  .input.routine__name {
    font-size: 2rem;
  }
}
</style>
