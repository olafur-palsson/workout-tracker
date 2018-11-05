import RequestFactory from './Request'
import Cookies from './Cookiehandler'

let Request = new RequestFactory()

// breyta thessum ef madur aetlar ad skipta milli server og localhost
// const baseString = "http://<ipTala>:8080/database/userEnabled/"
const baseString = 'http://localhost:8080/database/userEnabled/'

const requestBuilder = (methodName, requestParams = {}) => {
  const method = `${baseString}${methodName}`
  let params = requestParams
  if (typeof requestParams === 'object') {
    params = Object.keys(requestParams).map(key => key + '=' + requestParams[key]).join('&')
  }
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

// This basically only works if the object is flat, like ExerciseEntitiy
// I.e. no @ElementCollection and such
const makeNewEntity = (entityName, newEntityParams) => {
  return requestBuilder('add' + entityName, newEntityParams)
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
  newUser: async (name, email, password) => requestBuilder('newUser', { name, email, password }),
  getCurrentUserData: async () => getById('User', Cookies.getByName('email'))
}

const setListFunctions = {
  makeNewEntity: async (setList) => {
    // encode parameters
    let parameters = setList.reduce((encoding, set) => {
      return encoding + `&listOfWeights=${set.weight}&reps=${set.reps}`
    }, '')
    parameters = parameters.substring(1, parameters.length)
    return makeNewEntity('SetList', parameters)
  }
}

const routineFunctions = {
  makeNewEntity: async (exerciseIds, setListIds) => {
    let parameters
    console.log(exerciseIds)
    console.log(setListIds)
    parameters = exerciseIds.reduce((encoding, id) => encoding + `&exerciseIds=${id}`, '')
    parameters = setListIds.reduce((encoding, id) => encoding + `&setListIds=${id}`, parameters)
    return makeNewEntity('Routine', parameters)
  }
}

const user     = createFunctions('User', 'Users', userFunctions)
const routine  = createFunctions('Routine', 'Routines', routineFunctions)
const history  = createFunctions('History', 'Histories', {})
const exercise = createFunctions('Exercise', 'Exercises', {})
const setList  = createFunctions('SetList', 'SetLists', setListFunctions)

const logIn = (username, password) => Request.setCredentials(username, password)
const logOut = () => Request.unsetCredentials()
const isLoggedIn = async () => Request.isLoggedIn()

// Method to send all request first before resolving (just for speed)
const idsToObjects = async (idsAsArray, idToObjectFunction) =>  {
  return Promise.all(idsAsArray.map(idToObjectFunction))
}

// Method to send array of objects and return array of ids
const objectToIds = async (objects, objectToIdFunction) => {
  return Promise.all(objects.map(objectToIdFunction))
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
