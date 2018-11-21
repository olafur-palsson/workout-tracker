<!--

Blank compenent to use as a template when creating new components
(it's a basic 'hello world' compenent that has Blankynes instead of
hello world)

-->
<template>
  <div class="calendar__component">
    <div v-bind:class="{ calendar__container: true, transformed: transformationState > 1 }">
      <div v-bind:class="{ calendar: true, transformed: transformationState > 1, transformed2: transformationState === 3 }">

        <!-- UNTRANSFORMED -->
        <div
          v-bind:class="{ monthName: true, transparent: transformationState > 0 }"
          v-if="transformationState < 2"
        >
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
        <div
          v-if="transformationState < 2"
          v-bind:class="{ calendar__row: true, transparent: transformationState > 0 }"
          v-for="(week, i) in weeks"
          :key="i"
        >
          <div
            v-on:click="selectDay(day)"
            v-for="(day, i) in week"
            :key="day"
            v-bind:class="{
              calendar__day: true,
              calendar__selected: day === selectedDay && day > 0,
              calendar__weekend: (i === 0 || i === 6) && day > 0,
              calendar__hasTrainingSession: dayHasTrainingSession(day)
            }"
          >
            <p v-if="day > 0">{{ day }}</p>
            <div v-bind:class="{ tiny_dot: true, dayHasTrainingSession: (dayHasTrainingSession(day)) }">
            </div>
          </div>
        </div>
      <!-- TRANSFORMED -->
      <div
        v-bind:class="{ calendar__routine: true, transparent: transformationState < 3 }"
        v-if="transformationState > 1"
      >
        <div v-if="routine.length > 1" class="">
          <div v-for="(obj, i) in routine" :key="i">
            <div class="button__container">
              <button class="button" v-on:click="routine = obj"> {{ obj.name }} </button>
            </div>
          </div>
        </div>
        <div v-else class="routineViewContainer">
          <RoutineView/>
        </div>
        <div class="button__container bottom">
          <button class="button" v-on:click="untransformCalendar()"> Back </button>
        </div>
      </div>
    </div>
    <div v-bind:class="{
      calendar__description: true,
      transparent: transformationState > 0
    }">
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
import RoutineView from '@/components/RoutineView'

const DAY_IN_MS = 86400000
const monthNames = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']

export default {
  name: 'Calendar',
  components: {
    RoutineView
  },
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
      viewRoutine: false,
      transformationState: 0,
      history: null,
      routine: [],
      trainingSessions: {}
    }
  },
  async created () {
    this.updateYearAndMonth()
    let trainingRoutines = await Database.user.getAllTrainingRoutines()
    this.history = this.categorizeExercises(trainingRoutines)
  },
  methods: {
    // yes, this is ugly, but it's also safe and better than iterating
    // all the time, please refactor though
    // https://imgur.com/QuLpXfI.jpg
    categorizeExercises (trainingRoutines) {
      let storage = {}
      let i = 0
      trainingRoutines.forEach(routine => {
        let yoloSwaggins = 'yolo dolo' + (i++)
        const time = new Date(routine.timestamp)
        storage[time.getFullYear()] = storage[time.getFullYear()] || {}
        let yearStorage = storage[time.getFullYear()]
        yearStorage[time.getMonth()] = yearStorage[time.getMonth()] || { yoloSwaggins }
        let monthStorage = yearStorage[time.getMonth()]
        monthStorage[time.getDate()] = monthStorage[time.getDate()] || {}
        let dayStorage = monthStorage[time.getDate()]
        dayStorage[routine.timestamp] = routine
      })
      return storage
    },
    dayHasTrainingSession (day) {
      try {
        return typeof this.history[this.year][this.month][day]['Test if exists'] === 'undefined'
      } catch (didNotExist) {
        return false
      }
    },
    transformCalendar () {
      this.transformationState = 1
      setTimeout(() => {
        this.transformationState = 2
        setTimeout(() => {
          this.transformationState = 3
        }, 500)
      }, 500)
    },
    untransformCalendar () {
      this.transformationState = 2
      setTimeout(() => {
        this.transformationState = 1
        setTimeout(() => {
          this.transformationState = 0
        }, 500)
      }, 500)
    },
    selectDay (day) {
      if (day > 0) this.selectedDay = day
      this.viewRoutine = true
      console.log(day)
      if (this.dayHasTrainingSession(day)) {
        this.transformCalendar()
        let day = this.history[this.year][this.month][this.selectedDay]
        const arrayOfRoutines = Object.keys(day).map(key => day[key])
        if (arrayOfRoutines.length > 1) this.routine = arrayOfRoutines
        else this.routine = arrayOfRoutines[0]
      }
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
      // add elements for empty spots
      for (let i = 0; i < startOfMonth.getDay(); i++) {
        daysInMonth.push(-i)
      }
      // add days
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

.calendar__day.calendar__weekend {
  background-color: #D06079;
  border-radius: 0;
  border: 1px solid #151F26;
}

.calendar__day.calendar__selected {
  background-color: #5BB0D8;
  color: #151F26;
}

.calendar__component {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 600px;
  max-width: 100%;
}

.calendar__container {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: 100%;
}

.calendar__description {
  background-color: #5BB0D8;
  width: 200px;
  height: 330px;
  position: fixed;
  align-self: flex-end;
  border-radius: 0px 6px 6px 0px;
  padding-top: 20px;
  transition: opacity 0.5s;
}

.calendar {
  background-color: #151F26;
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 600px;
  height: 300px;
  padding: 30px;
  padding-top: 20px;
  margin: 10px 0px 10px 10px;
  border-radius: 6px;
  border-width: 2px;
  border-style: solid;
  border-color: #151F26;
  transition: border-color 1.5s;
  transition: height 0.5s;
}

@media screen and (max-width: 600px) {
  .calendar__container {
    display: flex;
    flex-direction: column-reverse;
    align-items: center;
    justify-content: center;
    box-sizing: border-box;
    max-width: 100%;
    padding: 10px;
    box-sizing: border-box;
    padding-top: 20vh;
    transition: padding-top 0.5s;
  }

  .calendar__row, .monthName {
    width: 300px;
    max-width: 300px;
    min-width: 0px;
    margin: 0px 0px 0px 0px;
  }

  .monthName {
    margin-right: 0px;
  }

  .calendar__container.transformed {
    padding-top: 10px;
  }

  .calendar {
    width: 100%;
    margin: 0px;
    align-items: center;
  }

  .calendar__description {
    align-self: flex-start;
    border-radius: 0px;
    top: 10vh;
    left: 0px;
    width: 100%;
    max-width: 100%;
    height: 25vh;
  }
}

.calendar.transformed {
  height: 100%;
  margin-right: 0px;
}

.calendar.transformed2 {
  border-color: #5BB0D8;
  transition: border-color 1.5s;
}

.calendar__row {
  display: flex;
  width: 100%;
  transition: opacity 0.5s;
}

.monthName {
  margin-bottom: 5px;
  width: 75%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  transition: opacity 0.5s;
}

@media screen and (min-width: 601px) {
  .monthName {
    margin-right: 200px;
  }
}

.monthName__text {
  min-width: 80px;
}

v-icon {
  height: 1rem;
  width: 1rem;
}

.calendar__routine {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  transition: opacity 0.5s;
  max-height: 100%;
  max-width: 100%;
  height: 100%;
  width: 100%;
}

.iconContainer {
  display: flex;
  cursor: pointer;
}

.transparent {
  opacity: 0;
}

.tiny_dot {
  position: absolute;
  align-self: flex-end;
  background-color: #e5f1f9;
  opacity: 0;
  width: 5px;
  height: 5px;
  border-radius: 100%;
}

.tiny_dot.dayHasTrainingSession {
  opacity: 1;
}

.routineViewContainer {
  overflow: auto;
  max-height: 90%;
  max-width: 100%;
  width: 100%;
}

.bottom {
  align-self: flex-end;
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
