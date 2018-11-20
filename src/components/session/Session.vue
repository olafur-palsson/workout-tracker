<!--

A container to handle the session, if no active session is
detected then the user is provided with an option to make
one from the list of routines he has collected/created.

-->
<template>
  <div class="session">
    <router-view/>
  </div>
</template>

<script>

import Database from '@/database/Database'

// TODO:
// 1. Setja active routine a userinn thegar thad er valid
// 2. Alltaf tekka hvort user a active routine
export default {
  name: 'Session',
  data () {
    return {
      routine: null,
      msg: 'Session'
    }
  },
  created () {
    this.checkIfActiveRoutine()
  },
  methods: {
    selectRoutine (selectedRoutine) {
    },
    async checkIfActiveRoutine () {
      const activeRoutine = await Database.user.getActiveRoutine()
      console.log('active routine is...')
      console.log(activeRoutine)
      if (activeRoutine) {
        this.routine = activeRoutine
        this.$router.push({ name: 'session_trainingView' })
      }
    },
    async newSession (selectedRoutine) {
      const copiedRoutine = await Database.routine.deepCopyRoutine(selectedRoutine.id)
      this.routine = copiedRoutine
      console.log('copy is...')
      console.log(this.routine.id)
      await Database.user.createHistoryEntry(this.routine.id)
      Database.user.setActiveRoutine(this.routine.id)
      this.$router.push({ name: 'session_trainingView' })
      return 'Lol'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.session {
  height: 100%;
  width: 100%;
  max-width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  overflow: auto

}
</style>
