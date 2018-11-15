// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import Vuetify from 'vuetify'
import '@mdi/font/css/materialdesignicons.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import setup from './setupDatabase'

setup()

const icons = {
  'info': 'mdi-dropbox',
  'left': 'mdi-chevron-left'
}

Vue.config.productionTip = false
Vue.use(Vuetify, {
  iconfont: 'mdi'
})


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
