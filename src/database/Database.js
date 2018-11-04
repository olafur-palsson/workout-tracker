import RequestFactory from './Request'

let Request = new RequestFactory()

// breyta thessum ef madur aetlar ad skipta milli server og localhost
// const baseString = "http://<ipTala>:8080/database/userEnabled/"
const baseString = 'http://localhost:8080/database/userEnabled/'

const requestBuilder = (methodName, requestParams = {}) => {
  const method = `${baseString}${methodName}`
  let params = Object.keys(requestParams).map(key => key + '=' + requestParams[key]).join('&')
  params = params ? '?' + params : ''
  return Request.make(method + params)
}

const getById = async (singularEntityName, id, idKey) => {
  const params = {}
  params[idKey || 'id'] = id
  return requestBuilder('one' + singularEntityName, params)
}

const getAll = (pluralEntityName) => {
  return requestBuilder('all' + pluralEntityName)
}

const makeNewEntity = (entityName, newEntityParams) => {
  return requestBuilder('new' + entityName, newEntityParams)
}

// ef allir hlutir eru med eitthvad function tha getur madur sett thad hingad
const createFunctions = (singularEntityName, pluralEntityName, specificFunctions) => {
  const functions = {
    // We put these methdos on all entity types
    getOne: async (id, idkey) => getById(singularEntityName, id, idkey),
    getAll: async () => getAll(pluralEntityName),
    makeNewEntity: async (newEntityParams) => makeNewEntity(singularEntityName, newEntityParams)
  }
  // specificFunctins overrides functions in the output object
  return Object.assign(functions, specificFunctions)
}

// Here is a specific function for 'user'
const userFunctions = {
  newUser: async (name, email, password) => requestBuilder('newUser', { name, email, password })
}

const user     = createFunctions('User', 'Users', userFunctions)
const routine  = createFunctions('Routine', 'Routines', {})
const history  = createFunctions('History', 'Histories', {})
const exercise = createFunctions('Exercise', 'Exercises', {})
const setList  = createFunctions('SetList', 'SetLists', {})

const logIn = (username, password) => Request.setCredentials(username, password)
const logOut = () => Request.unsetCredentials()
const isLoggedIn = async () => Request.isLoggedIn()

/* Nota thetta til ad gera async map
const arr = [ { key: 1 }, { key: 2 }, { key: 3 } ]

const results = arr.map(async (obj) => { return obj.key; });
// document.writeln( `Before waiting: ${results}`);

Promise.all(results).then((completed) => document.writeln( `\nResult: ${completed}`));
*/

// Method to send all request first before resolving (just for speed)
const idsToObjects = async (idsAsArray, idToObjectFunction) =>  {
  Promise.all(idsAsArray.map(idToObjectFunction))
}

const objectToIds = async (objects, objectToIdFunction) => {
  console.log('TODO: Make objectToIds in Database.js')
}

const database = {
  routine,
  user,
  history,
  exercise,
  setList,
  logIn,
  logOut,
  isLoggedIn,
  objectToIds,
  idsToObjects
}

export default database
