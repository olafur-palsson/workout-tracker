<!--

Blank compenent to use as a template when creating new components
(it's a basic 'hello world' compenent that has Blankynes instead of
hello world)

-->
<template>
  <div class="calendar__component">
    {{ history }}
    <div class="calendar__container">
      <div class="calendar">
        <div class="monthName">
          <span class="iconContainer">
            <v-icon v-on:click="updateYearAndMonth(-1)">chevron_left</v-icon>
          </span>
          <div class="monthName__text">
            {{ monthName }}
          </div>
          <span class="iconContainer">
            <v-icon v-on:click="updateYearAndMonth(1)">chevron_right</v-icon>
          </span>
        </div>
        <div class="calendar__row" v-for="(week, i) in weeks" :key="i">
          <div
          v-bind:class="{ calendar__day: true, calendar__selected: day === selectedDay && day > 0}"
          v-on:click="selectDay(day)"
          v-for="day in week"
          :key="day"
          >
          <p v-if="day > 0">{{ day }}</p>
        </div>
      </div>
    </div>
    <div class="calendar__description">
      <div class="calendar__description__date">
        {{ selectedDay }}
      </div>
      {{ year }} , {{ month }}
    </div>
  </div>

</div>
</template>

<script>

import Database from '@/database/Database'

const DAY_IN_MS = 86400000
const monthNames = ["January","February","March","April","May","June","July",
"August","September","October","November","December"];

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
      selectedDay: 1,
      monthName: null,
      history: 'Still buggy'
    }
  },
  async created () {
    this.updateYearAndMonth()
    this.history = await Database.user.getHistoryOfUser()
  },
  methods: {
    selectDay (day) {
      if (day > 0) this.selectedDay = day
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
        if (i % 7 === 0) weeks.push(daysInMonth.slice(i, i + 7))
      })
      this.monthName = monthNames[this.month]
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
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.calendar__description__date {
  font-size: 70px;
  font-weight: 700;
}

.calendar__day {
  width: 40px;
  height:   40px;
  display: flex;
  justify-content: space-between;
  padding: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: color 0.3s ease-in;
  transition: background-color 0.3s ease-in;
  border-radius: 100%;
}

.calendar__selected {
  background-color: #5BB0D8;
  color: #151F26;
}

.calendar__component {
  display: flex;
  justify-content: center;
  align-items: center;
}

.calendar__container {
  display: flex;
  width: 600px;
  height: 330px;
}

.calendar__description {
  background-color: #5BB0D8;
  width: 200px;
  border-radius: 0px 6px 6px 0px;
  padding-top: 20px;
}

.calendar {
  background-color: #151F26;
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 400px;
  padding: 30px;
  padding-top: 20px;
  margin: 10px 0px 10px 10px;
  border-radius: 6px 0px 0px 6px;
}

.calendar__row {
  display: flex;
  width: 100%;
}

.monthName {
  margin-bottom: 5px;
  width: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.monthName__text {
  min-width: 80px;
}

v-icon {
  height: 1rem;
  width: 1rem;
}

.iconContainer {
  display: flex;
  cursor: pointer;
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
