
<template>
  <div class="blank">

    <h1> Session </h1>
    <router-view/>
  </div>
</template>

<script>

import Database from '@/database/Database'

export default {
  name: 'Session',
  data () {
    return {
      routine: null,
      msg: 'Session'
    }
  },
  methods: {
    selectRoutine (selectedRoutine) {
    },
    async newSession (selectedRoutine) {
      const copiedRoutine = await Database.routine.deepCopyRoutine(selectedRoutine.id)
      this.routine = copiedRoutine
      await Database.user.createHistoryEntry(this.routine.id)
      this.$router.push({ name: 'session_routineView' })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
