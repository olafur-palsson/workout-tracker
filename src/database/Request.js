
import Cookies from './Cookiehandler'

const printRequestOnSend = true

class RequestFactory {
  async isLoggedIn () {
    // return true if user is logged in
    const answer = await this.make('http://localhost:8080/database/userEnabled/check')
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
    }
    this.loadCookies()
  }

  make (requestString) {
    if (printRequestOnSend) {
      console.log('REQUEST only works for JSON')
      console.log(requestString)
    }

    const req = new XMLHttpRequest()
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
      req.onload    = () => {
        try {
          resolve(JSON.parse(req.responseText))
        } catch (error) {
          resolve(req.responseText)
        }
      }
    }).catch(error => console.log('Error sending request:  \n  ' + error.message))
  }
}

export default RequestFactory