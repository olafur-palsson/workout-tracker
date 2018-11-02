
class RequestBuilder {
  constructor () {
    this.make = this.make.bind(this)
    this.setCredentials = this.setCredentials.bind(this)
    this.username = null
    this.password = null
  }

  setCredentials (usern, passw) {
    this.username = usern
    this.password = passw
  }

  make (requestString) {
    console.log('REQUEST only works for JSON')
    console.log(requestString)
    const req = new XMLHttpRequest()
    const auth = 'Basic ' + Buffer.from(this.username + ':' + this.password).toString('base64')
    req.open('GET', requestString, true)
    if (this.username && this.password) {
      req.setRequestHeader('Authorization', auth)
    }
    req.send()
    return new Promise((resolve, reject) => {
      req.ontimeout = () => reject(new Error('Request timed out'))
      req.onload = () => {
        try {
          resolve(JSON.parse(req.responseText))
        } catch (error) {
          resolve(req.responseText)
        }
      }
      req.onerror = () => reject(new Error('Request status: ' + req.status))
    })
  }
}

export default RequestBuilder
