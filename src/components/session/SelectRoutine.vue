<!--

    Renders the list of routines available to the user
    TOOD: allow for editing the routine

-->
<template>
  <div class="session__select__routine">
    <div class="session__routine__chooser" v-if="allRoutines.length != 0">
      <p class="heading"> Pick a routine </p>
      <div class="session__routine" v-for="(routine, index) in allRoutines" :key="index">
        <p class="session__routine__name">{{ routine.name }}</p>
        <div class="button__container">
          <button class="button" v-on:click="createSession(routine)"> Train </button>
        </div>
        <div class="button__container">
          <button class="button" v-on:click="viewRoutine(routine)"> View </button>
        </div>
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
    this.getAllRoutines()
  },
  methods: {
    async getAllRoutines () {
      const userData = await Database.user.getCurrentUserData()
      this.allRoutines = await Database.idsToObjects(userData.personalRoutines, id => {
        return Database.routine.getOne(id)
      })
    },
    async createSession (routine) {
      this.$parent.newSession(routine)
    },
    gotoNewRoutine () {
      this.$router.push({ name: 'newRoutine' })
    },
    allRoutinesIsEmpty () {
      console.log(this.allRoutines)
      if (this.allRoutines === undefined) return true
      if (this.allRoutines === null) return true
      if (this.allRoutines.length < 1) return true
      console.log('went through all tests')
      return false
    },
    viewRoutine (routine) {
      this.$parent.routine = routine
      this.$router.push({ name: 'session_routineView' })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.session__select__routine {
  width: 100%;
  max-width: 600px;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.session__routine__chooser {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.session__routine__name {
  font-size: 1.25rem;
  width: 100%;
  flex-grow: 2;
}

.session__routine {
  width: 100%;
  display: flex;
  align-items: center;
}

.button__container {
  max-width: 33%;
}
</style>
