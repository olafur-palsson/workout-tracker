/*

    A simple tool to set and get cookies of the browser

*/
const setCookie = (name, value, days) => {
  let expires = ''
  if (days) {
    let date = new Date()
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000))
    expires = '; expires=' + date.toUTCString()
  }
  document.cookie = name + '=' + (value || '')  + expires + '; path=/'
}

const allCookies = () => {
  let cookieArray = document.cookie.split(';')
  let cookieObject = {}
  cookieArray.forEach(cookie => {
    while (cookie.charAt(0) === ' ') {
      cookie = cookie.substring(1, cookie.length)
    }
    const cookiePair = cookie.split('=')
    cookieObject[cookiePair[0]] = cookiePair[1]
  })
  return cookieObject
}

const deleteCookie = name => {
  document.cookie = name + '=; Max-Age=-99999999;'
}

const getCookie = cookieName => {
  return allCookies()[cookieName]
}

const Cookies = {
  delete: deleteCookie,
  getByName: getCookie,
  toObject: allCookies,
  set: setCookie
}

export default Cookies
