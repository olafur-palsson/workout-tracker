<!--

    Blank compenent to use as a template when creating new components
    (it's a basic 'hello world' compenent that has Blankynes instead of
    hello world)

-->
<template>
  <div class="blank">
    <h1> This is blank </h1>
    {{ msg }}
    <div class="" v-for="day in daysInMonth" :key="day">
      {{ day }}
    </div>
    <button v-on:click="updateYearAndMonth(1)"> Next month </button>
    <button v-on:click="updateYearAndMonth(-1)"> Previous month </button>
  </div>
</template>

<script>

const DAY_IN_MS = 86400000

export default {
  name: 'Blank',
  data () {
    return {
      msg: 'Blankyness',
      month: null,
      year: null,
      monthOffset: 0,
      daysInMonth: []
    }
  },
  created () {
    this.updateYearAndMonth()
  },
  methods: {
    updateYearAndMonth (offset = 0) {
      this.monthOffset += offset
      const now = new Date()
      const currentYear = now.getFullYear()
      const currentMonth = now.getMonth()
      this.year = currentYear + Math.floor((currentMonth + this.monthOffset) / 12)
      this.month = (currentMonth + this.monthOffset) % 12
      if (this.month < 0) this.month += 12
      this.daysInMonth = this.getDaysInMonth()
    },
    getDaysInMonth () {
      const startOfMonth = new Date(this.year, this.month)
      let iterator = startOfMonth.getTime()
      let daysInMonth = []
      while ((new Date(iterator)).getMonth() === this.month) {
        const date = new Date(iterator)
        daysInMonth.push(date.getDate())
        iterator += DAY_IN_MS
      }
      return daysInMonth
    },
    getDayAndMonth (dateInMs) {
      const date = new Date(dateInMs)
      let utc = date.toUTCString().split(' ')
      const dayName = utc[0].slice(0, utc[0].length - 1)
      const monthName = utc[2]
      return [dayName, monthName]
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
