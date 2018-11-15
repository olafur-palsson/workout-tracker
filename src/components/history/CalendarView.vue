<!--

Blank compenent to use as a template when creating new components
(it's a basic 'hello world' compenent that has Blankynes instead of
hello world)

-->
<template>
  <div class="calendar__component">
    <h1> This is blank </h1>
    <div class="calendar__container">
      <div class="calendar">
        <div class="calendar__row" v-for="(week, i) in weeks" :key="i">
          <div class="calendar__day" v-on:click="selectDay(day)" v-for="day in week" :key="day">
            <p v-if="day > 0">{{ day }}</p>
          </div>
        </div>
      </div>
      <div class="calendar__description">
        {{ selectedDay }} {{ year }} , {{ month }}
      </div>
    </div>
    <div class="button__container">
      <button class="button" v-on:click="updateYearAndMonth(1)"> Next month </button>
    </div>
    <div class="button__container">
      <button class="button" v-on:click="updateYearAndMonth(-1)"> Previous month </button>
    </div>

  </div>
</template>

<script>

const DAY_IN_MS = 86400000

export default {
  name: 'Calendar',
  data () {
    return {
      msg: 'Blankyness',
      month: null,
      year: null,
      weekday: null,
      monthOffset: 0,
      weeks: [],
      selectedDay: 1
    }
  },
  created () {
    this.updateYearAndMonth()
  },
  methods: {
    selectDay (day) {
      this.selectedDay = day
    },
    updateYearAndMonth (offset = 0) {
      this.monthOffset += offset
      const now = new Date()
      const currentYear = now.getFullYear()
      const currentMonth = now.getMonth()
      this.year = currentYear + Math.floor((currentMonth + this.monthOffset) / 12)
      this.month = (currentMonth + this.monthOffset) % 12
      if (this.month < 0) this.month += 12
      const daysInMonth = this.getDaysInMonth()
      let weeks = []
      daysInMonth.forEach((_, i) => {
        if(i % 7 === 0) weeks.push(daysInMonth.slice(i, i + 7))
      })
      this.weeks = weeks
    },
    getDaysInMonth () {
      const startOfMonth = new Date(this.year, this.month)
      let iterator = startOfMonth.getTime()
      let daysInMonth = []
      console.log(startOfMonth.getDay())
      for (let i = 0; i < startOfMonth.getDay(); i++) {
        daysInMonth.push(-i)
      }
      while ((new Date(iterator)).getMonth() === this.month) {
        const date = new Date(iterator)
        daysInMonth.push(date.getDate())
        iterator += DAY_IN_MS
      }
      return daysInMonth
    },
    getDayAndMonthAndWeekday (dateInMs) {
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

.calendar__component {
  display: flex;
  justify-content: center;
  align-items: center;
}

.calendar__container {
  display: flex;
  width: 600px;
  height: 300px;
}

.calendar__description {
  background-color: #5BB0D8;
  width: 200px;
  border-radius: 0px 6px 6px 0px;
}

.calendar {
  background-color: #151F26;
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 400px;
  padding: 30px;
  margin: 10px 0px 10px 10px;
  border-radius: 6px 0px 0px 6px;
}

.calendar__row {
  display: flex;
  width: 100%;
}

.calendar__day {
  width: 40px;
  height:   40px;
  display: flex;
  justify-content: space-between;
  padding: 5px;
}



/*
transform: rotateY(180deg)
transition: transform 0.5s ease-in
colors {
#e5f1f9;
#5BB0D8;
#151F26;
}
*/

</style>
