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
import RoutineView from '@/components/RoutineView'
import AddExercise from '@/components/AddExercise'

// Create Routine Classes
import NewRoutine from '@/components/newRoutine/NewRoutine'
import NewRoutineMain from '@/components/newRoutine/NewRoutine_main'

// Show the history
import History from '@/components/history/History'

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
      component: AddExercise
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
      path: '/session_training',
      name: 'session_trainingView',
      component: TrainingView
    }, {
      path: '/session_routine',
      name: 'session_routineView',
      component: RoutineView,
      children: [{
        path: '/addExercise',
        name: 'session_routine_addExercise',
        component: AddExercise
      }]
    }]
  }, {
    // History
    path: '/history',
    component: History,
    name: 'history',
    children: [{
      path: '/addExercise',
      name: 'history_routine_addExercise',
      component: AddExercise
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
