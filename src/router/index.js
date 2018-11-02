import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from '@/components/login_components/Login'
import LoginForm from '@/components/login_components/LoginForm'
import SignupForm from '@/components/login_components/SignupForm'
import CheckLogin from '@/components/CheckLogin'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'checklogin',
      component: CheckLogin
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      component: Login,
      children: [
        {
          path: 'signup',
          name: 'signupform',
          component: SignupForm
        },
        {
          path: '',
          name: 'login',
          component: LoginForm
        }
      ]
    }
  ]
})
