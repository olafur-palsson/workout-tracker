/*

    A class that contains the credentials of the user
    it also manages setting the cookie to have a global access
    to user's email.

    This class always returns a promise when making requests

*/
import Cookies from './Cookiehandler'

const printRequestOnSend = true

class RequestFactory {
  async isLoggedIn () {
    // return true if user is logged in
    const answer = await this.make('http://68.183.127.104:8080/database/userEnabled/check')
    console.log(answer)
    return answer === 'logged_in'
  }

  loadCookies () {
    const cookies = Cookies.toObject()
    console.log(cookies)
    if (cookies.email && cookies.password) {
      this.email = cookies.email
      this.password = cookies.password
    }
  }

  unsetCredentials () {
    console.log('Logging out')
    Cookies.delete('email')
    Cookies.delete('password')
    this.email = null
    this.password = null
  }

  constructor () {
    this.make = this.make.bind(this)
    this.setCredentials = this.setCredentials.bind(this)
    this.isLoggedIn = this.isLoggedIn.bind(this)
    this.unsetCredentials = this.unsetCredentials.bind(this)
    this.email = null
    this.password = null
    this.loadCookies()
  }

  setCredentials (email, password, stayLoggedIn = false) {
    if (email && password) {
      Cookies.set('email', email, stayLoggedIn)
      Cookies.set('password', password, stayLoggedIn)
      this.loadCookies()
    } else {
      this.unsetCredentials()
    }
  }

  make (requestString) {
    requestString = encodeURI(requestString)
    if (printRequestOnSend) {
      console.log(requestString)
    }
    const req = new XMLHttpRequest()
    req.timeout = 10000
    req.open('GET', requestString, true)
    console.log(this.email, this.password)
    if (this.email && this.password) {
      const auth = 'Basic ' + Buffer.from(this.email + ':' + this.password).toString('base64')
      req.setRequestHeader('Authorization', auth)
    }
    req.send()

    return new Promise((resolve, reject) => {
      req.ontimeout = () => reject(new Error('Request timed out'))
      req.onerror   = () => reject(new Error('Request status: ' + req.status))
      req.ontimeout = () => reject(new Error('Server timeout after 2 seconds'))
      req.onload    = () => {
        console.log('Success')
        try {
          const json = JSON.parse(req.responseText)
          resolve(json)
        } catch (error) {
          resolve(req.responseText)
        }
      }
    }).catch(error => console.log('Error sending request:  \n  ' + error.message))
  }
}

export default RequestFactory
