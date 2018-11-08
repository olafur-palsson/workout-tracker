
<template>
  <div>
    <h1> {{ msg }} </h1>
    <h1> What the... </h1>
    <button v-on:click="logAllRoutines()"> Log routines </button>
    <div v-if="allRoutines">
      Not empty
      <div v-for="(routine, index) in allRoutines" :key="index">
        {{ routine.name }}
        <button v-on:click="createSession(routine)"> Train </button>
        <button v-on:click="viewRoutine(routine)"> View </button>
      </div>
    </div>
    <div v-else>
      <h1> You don't have any routines yet.</h1>
      <button v-on:click="gotoNewRoutine()"> Go make one </button>
      <h3> or </h3>
      <button> Browser for one (does nothing) </button>
    </div>
  </div>
</template>

<script>
import Database from '@/database/Database'

export default {
  name: 'SelectRoutine',
  data () {
    return {
      allRoutines: [],
      notMsg: 'Something',
      msg: 'Select routine bruh'
    }
  },
  created () {
    console.log('DING')
    this.getAllRoutines()
  },
  methods: {
    async getAllRoutines () {
      console.log('Triggered getAllRoutines')
      const userData = await Database.user.getCurrentUserData()
      console.log(userData)
      this.allRoutines = await Database.idsToObjects(userData.personalRoutines, id => {
        return Database.routine.getOne(id)
      })
      console.log(this.allRoutines)
    },
    viewRoutine (routine) {
      console.log('View routine is not implemented')
      console.log(routine)
      throw Error('viewRoutine <-- SelectRoutine is not implemented yet')
    },
    logAllRoutines () {
      console.log('All routines:')
      console.log(this.allRoutines)
    },
    createSession (routine) {
      this.$parent.newSession(routine)
      this.$router.go(-1)
    },
    gotoNewRoutine () {
      this.$router.push({ name: 'newRoutine' })
    },
    allRoutinesIsEmpty () {
      console.log(this.allRoutines)
      if (this.allRoutines === undefined) return true
      if (this.allRoutines === null) return true
      if (this.allRoutines.length < 1) return true
      return false
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
