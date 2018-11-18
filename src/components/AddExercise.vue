<!--

    Renders a form to add an exercise to the routine being created
    upon pressing Confirm it pushes the changes to the routine object
    on NewRoutine

-->
<template>
  <div class="addExercise">

    <div v-if="selectedExercise == null">
      <h1 class="hint">Select exercise</h1>
      <div class="button__container ">
        <button class="button exercise" v-for="(exercise, i) in this.$parent.allExercises" :key="i" v-on:click="selectExercise(exercise)">
          {{ exercise.name }}
        </button>
      </div>
    </div>

    <div v-else>
      <h1 class="hint"> {{ selectedExercise.name }} </h1>

      <div class="addExercise__control">
        <div class="form">
          <div class="form__input">
            Reps <input class="input" v-model="currentSetListInput.reps" placeholder="reps">
          </div>
          <div class="form__input">
            Weight <input class="input" v-model="currentSetListInput.weight" placeholder="weight">
          </div>
        </div>
        <div class="button__container">
          <button class="button" v-on:click="addSet()"> Add Set </button>
        </div>

      </div>

      <div class="addExercise__set" v-for="(unused, i) in setList.listOfReps" :key="i">
        <div class="set__item">
          <div class="icon__weight icon"></div>
          {{ setList.listOfWeights[i] }}
        </div>
        <div class="set__item">
          <span class="alegreyasans"> Reps: </span>
          {{ setList.listOfReps[i] }}
        </div>
        <div class="button__container">
          <button class="button button__red" v-on:click="removeSet(i)"> X </button>
        </div>
      </div>
    </div>

    <div class="button__container">
      <button class="button button__green" v-on:click="sendToParent()"> Confirm </button>
    </div>
    <div class="button__container">
      <button class="button button__red" v-on:click="goBack()"> Cancel </button>
    </div>
  </div>
</template>

<script>

export default {
  // name: 'CreateRoutine_addexercise',
  data () {
    return {
      currentSetListInput: { weight: 20, reps: 8 },
      selectedExercise: null,
      setList: {
        listOfWeights: [],
        listOfReps: []
      },
      msg: 'NewRoutine_addExercise'
    }
  },
  methods: {
    goBack () {
      this.$router.go(-1)
    },
    removeSet (index) {
      this.setList.reps.splice(index, 1)
      this.setList.weight.splice(index, 1)
    },
    selectExercise (selectedExercise) {
      this.selectedExercise = selectedExercise
    },
    sendToParent () {
      // exercise will always be the same object, so we copy to send it
      const selectedExerciseCopy = Object.assign({}, this.selectedExercise)
      // send to routine gathering
      this.$parent.addExerciseToRoutine(selectedExerciseCopy, this.setList)
      // setList will always be a new object
      this.setList = [Object.assign({}, this.currentSetListInput)]
      this.selectedExercise = { listOfReps: [], listOfWeights: [] }
      this.goBack()
    },
    addSet () {
      this.setList.listOfReps.push(this.currentSetListInput.reps)
      this.setList.listOfWeights.push(this.currentSetListInput.weight)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.addExercise {
  width: 100%;
}

.input {
  background-color: rgba(0, 0, 0, 0.3);
  font-size: 1rem;
  border-width: 0;
  color: #CEDAE2;
  border-radius: 3px;
  margin-bottom: 20px;
  text-align: center;
}

.addExercise {
  padding-bottom: 100px;
}

.button.exercise {
  width: 100px;
}

.set__item {
  width: 3.3rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.addExercise__set {
  display: flex;
  height: 1.8rem;
  width: 500px;
  justify-content: space-around;
}

.addExercise__set .button__container {
  max-width: 20%;
}

.hint {
  font-size: 2rem;
  margin-bottom: 20px;
  margin-top: 40px;
}


.icon.icon__weight {
  background-position: center;
  background-size: cover;
  width: 1.5rem;
  height: 1.5rem;
}

.button__red.button {
  background-color: #D06079;
}

.button__green.button {
  background-color: #73AF55;
}

</style>
