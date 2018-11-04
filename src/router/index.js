import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/components/Home'
import CheckLogin from '@/components/CheckLogin'

import Login from '@/components/login/Login'
import LoginForm from '@/components/login/LoginForm'
import SignupForm from '@/components/login/SignupForm'

import NewRoutine from '@/components/newRoutine/NewRoutine'
import NewRoutineMain from '@/components/newRoutine/NewRoutine_main'
import NewRoutineAddExercise from '@/components/newRoutine/NewRoutine_addExercise'

Vue.use(Router)

export default new Router({
  routes: [{
    path: '/',
    name: 'checkLogin',
    component: CheckLogin
  }, {
    // Home
    path: '/home',
    name: 'home',
    component: Home,
    children: [{
      // newRoutine
      path: 'newRoutine',
      component: NewRoutine,
      children: [{
        path: '',
        name: 'newRoutine',
        component: NewRoutineMain
      }, {
        path: '/newRoutine_addExercise',
        name: 'newRoutine_addExercise',
        component: NewRoutineAddExercise
      }]
    }]
  }, {
    // Log in routes
    path: '/login',
    component: Login,
    children: [{
      path: 'signup',
      name: 'signupForm',
      component: SignupForm
    }, {
      path: '',
      name: 'login',
      component: LoginForm
    }]
  }]
})
