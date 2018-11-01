
import test from "./test.js"
import Vue from 'vue'

test()

new Vue({
  el: '#app',
  data: {
    message: 'Hello there',
    something: 'Lol'
  }
})

new Vue({
  el: '#test',
  data: {
    somethingelse: 'Nothing'
  }
})
