import Vue from 'vue'
import Router from 'vue-router'

// Almost empty containers
import Home from '@/components/Home'
import CheckLogin from '@/components/CheckLogin'

// Login classes
import Login from '@/components/login/Login'
import LoginForm from '@/components/login/LoginForm'
import SignupForm from '@/components/login/SignupForm'

// Session classes
import Session from '@/components/session/Session'
import SelectRoutine from '@/components/session/SelectRoutine'
// Data renderers
import TrainingView from '@/components/session/TrainingView'

// Create Routine Classes
import NewRoutine from '@/components/newRoutine/NewRoutine'
import NewRoutineMain from '@/components/newRoutine/NewRoutine_main'
import NewRoutineAddExercise from '@/components/newRoutine/NewRoutine_addExercise'

// Show the history
import ShowHistory from '@/components/history/ShowHistory'

Vue.use(Router)

export default new Router({
  routes: [{
    path: '',
    name: 'checkLogin',
    component: CheckLogin
  }, {
    // Home
    path: '/home',
    name: 'home',
    component: Home
  }, {
    path: '/newRoutine',
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
  }, {
    // Session
    path: '/session',
    component: Session,
    children: [{
      path: '',
      name: 'session',
      component: SelectRoutine
    }, {
      path: '/session_routine',
      name: 'session_trainingView',
      component: TrainingView
    }]
  }, {
    // History
    path: '/history',
    component: ShowHistory,
    children: [{
      path: '',
      name: 'history',
      component: ShowHistory
    }, {
      path: '/routine',
      name: 'history_routine',
      component: TrainingView
    }, {
      path: 'exercise'
    }]
  }, {
    // Login routes
    path: '/login',
    component: Login,
    children: [{
      path: '/signup',
      name: 'signupForm',
      component: SignupForm
    }, {
      path: '',
      name: 'login',
      component: LoginForm
    }]
  }]
})
