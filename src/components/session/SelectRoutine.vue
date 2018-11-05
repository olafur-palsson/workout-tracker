
<template>
  <div class="blank">
    <h1> {{ msg }} </h1>

    <div v-if="allRoutines.length" v-for="(routine, index) in allRoutines" :key="index">
      {{ routine.name }}
      <button v-on:click="selectRoutine(routine)"> {{ Train }} </button>
      <button v-on:click="viewRoutine(routine)"> {{ View }} </button>
    </div>
    <div v-else>
      <h1> You don't have any routines yet.</h1>
      <button v-on:click="gotoNewRoutine()"> Go make one </button>
      <h3> or </h3>
      <button> Browser for one (does nothing) </button>
    </div>
    {{ msg }}
  </div>
</template>

<script>
import Database from '@/database/Database'

export default {
  name: 'SelectRoutine',
  allRoutines: null,
  data () {
    return {
      msg: 'SelectRoutine'
    }
  },
  methods: {
    async getAllRoutines () {
      const userData = await Database.user.getCurrentUserData()
      this.allRoutines = await Database.idsToObjects(userData.personalRoutines, id => {
        return Database.routine.getById(id)
      })
    },
    viewRoutine (routine) {
      console.log('View routine is not implemented')
      console.log(routine)
      throw Error('viewRoutine <-- SelectRoutine is not implemented yet')
    },
    selectRoutine (routine) {
      this.$parent.selectRoutine(routine)
      this.$router.go(-1)
    },
    gotoNewRoutine () {
      this.$router.go({ name: 'newRoutine' })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
