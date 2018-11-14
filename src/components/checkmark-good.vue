<!--

Blank compenent to use as a template when creating new components
(it's a basic 'hello world' compenent that has Blankynes instead of
hello world)

-->
<template>
  <div v-bind:style="checkmarkStyles" v-bind:class="{ checkmark__container: true, transparent }">
    <svg v-bind:style="svgStyles" version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 130.2 130.2">
      <circle
      class="path circle"
      fill="none"
      stroke="#73AF55"
      stroke-width="6"
      stroke-miterlimit="10"
      cx="65.1"
      cy="65.1"
      r="62.1" />
      <polyline
      class="path check"
      fill="none"
      stroke="#73AF55"
      stroke-width="6"
      stroke-linecap="round"
      stroke-miterlimit="10"
      points="100.2,40.2 51.5,88.8 29.8,67.5 " />
    </svg>
  </div>
</template>

<script>
export default {
  name: 'GoodCheck',
  data () {
    return {
      message: this.$attrs.message,
      transparent: false,
      checkmarkStyles: {},
      svgStyles: {}
    }
  },
  created () {
    this.setStyles()
    setTimeout(() => {
      this.transparent = true
    }, 1000)
  },
  methods: {
    setStyles () {
      const size = this.$attrs.size
      this.svgStyles = {
        minWidth: size / 2 + 'px',
        minHeight: size / 2 + 'px',
        margin: size / 5 +  'px auto 0'
      }
      this.checkmarkStyles = {
        width: size + 'px',
        height: size + 'px',
        minHeight: size + 'px',
        minWidth: size + 'px'
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.transparent {
  opacity: 0;
}

.checkmark__container {
  border-radius: 3px;
  overflow: hidden;
  position: fixed;
  display: flex;
  justify-content: center;
  flex-direction: column;
  transition: opacity 0.5s;
}

svg {
  display: block;
}
.path {
  stroke-dasharray: 1000;
  stroke-dashoffset: 0;
}
.path.circle {
  -webkit-animation: dash 0.9s ease-in-out;
  animation: dash 0.9s ease-in-out;
}
.path.line {
  stroke-dashoffset: 1000;
  -webkit-animation: dash 0.9s 0.35s ease-in-out forwards;
  animation: dash 0.9s 0.35s ease-in-out forwards;
}
.path.check {
  stroke-dashoffset: -100;
  -webkit-animation: dash-check 0.9s 0.35s ease-in-out forwards;
  animation: dash-check 0.9s 0.35s ease-in-out forwards;
}
p {
  text-align: center;
  margin: 20px 0 60px;
  font-size: 1.25em;
}
p.success {
  color: #73AF55;
}
p.error {
  color: #D06079;
}
@-webkit-keyframes dash {
  0% {
    stroke-dashoffset: 1000;
  }
  100% {
    stroke-dashoffset: 0;
  }
}
@keyframes dash {
  0% {
    stroke-dashoffset: 1000;
  }
  100% {
    stroke-dashoffset: 0;
  }
}
@-webkit-keyframes dash-check {
  0% {
    stroke-dashoffset: -100;
  }
  100% {
    stroke-dashoffset: 900;
  }
}
@keyframes dash-check {
  0% {
    stroke-dashoffset: -100;
  }
  100% {
    stroke-dashoffset: 900;
  }
}
</style>
