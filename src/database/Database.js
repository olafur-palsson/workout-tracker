/*

    This class creates function corresponding to the controllers
    of the database. It uses the Cookies object to handle the Cookies
    in a stable way and RequestFactory to send a generic request from a string

*/
import RequestFactory from './Request'
import Cookies from './Cookiehandler'

let Request = new RequestFactory()

// breyta thessum ef madur aetlar ad skipta milli server og localhost
// const baseString = 'http://<ipTala>:8080/database/userEnabled/'

const baseString = 'http://localhost:8080/database/userEnabled/'
const newUserMethod = 'http://localhost:8080/database/newUser'

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
const saveEntity = (entityName, newEntityParams) => {
  return requestBuilder('add' + entityName, newEntityParams)
}

// These functions are common to all entities, it essentially generates functions as object
// to be exported in the Database module
const createFunctions = (singularEntityName, pluralEntityName, specificFunctions) => {
  const functions = {
    // We put these methdos on all entity types
    getOne: async (id, idkey) => getById(singularEntityName, id, idkey),
    getAll: async () => getAll(pluralEntityName),
    saveEntity: async (newEntityParams) => saveEntity(singularEntityName, newEntityParams)
  }
  // specificFunctins overrides functions in the output object
  return Object.assign(functions, specificFunctions)
}

// Here we define specific functions for the user
const userFunctions = {
  setActiveRoutine: async (routineId) => {
    const email = Cookies.getByName('email')
    requestBuilder('setActiveRoutine', { email, routineId })
  },
  getActiveRoutine: async () => {
    const email = Cookies.getByName('email')
    return requestBuilder('getActiveRoutine', { email })
  },
  newUser: async (name, email, password) => {
    Request.setCredentials('anonymous', 'anonymous')
    await Request.make(`${newUserMethod}?name=${name}&email=${email}&password=${password}`)
    Request.setCredentials(email, password)
  },
  getCurrentUserData: async () => getById('User', Cookies.getByName('email')),
  addRoutineToUser: async routineId => {
    const email = Cookies.getByName('email')
    requestBuilder('addRoutineToUser', { routineId, email })
  },
  createHistoryEntry: async routineId => {
    const email = Cookies.getByName('email')
    requestBuilder('createHistoryEntry', { routineId, email })
  }
}

// A simple encoding tool that makes a string with format
//     &parametername=value1&parameterName=value2&...&parameterName=valueN
const arrayToParameters = (parameterName, arrayOfElements) => {
  return arrayOfElements.reduce((encoding, element) => {
    return encoding + `&${parameterName}=${element}`
  }, '')
}

// Specific function for the setList entity
const setListFunctions = {
  // We need to convert the arrays in the setlist to strings
  // before we can send it to 'saveEntity'
  saveEntity: async (setList) => {
    // encode parameters
    let parameters = ''
    parameters += arrayToParameters('weight', setList.listOfWeights)
    parameters += arrayToParameters('reps', setList.listOfReps)
    if (setList.finishedSets) {
      parameters += arrayToParameters('setIsDone', setList.finishedSets)
    }
    if (setList.id) {
      parameters += `&id=${setList.id}`
    }
    parameters = parameters.substring(1, parameters.length)
    return saveEntity('SetList', parameters)
  }
}

// Specific functions to the routine entity
const routineFunctions = {
  // A saveEntity function for the routine object
  saveEntity: async (exerciseIds, setListIds, id = undefined) => {
    let parameters
    parameters = exerciseIds.reduce((encoding, id) => encoding + `&exerciseIds=${id}`, '')
    parameters = setListIds.reduce((encoding, id) => encoding + `&setListIds=${id}`, parameters)
    if (id) parameters += `&id=${id}`
    return saveEntity('Routine', parameters)
  },
  // Make the database deepCopy a routine, used to make a new session
  deepCopyRoutine: async routineId => {
    return requestBuilder('deepCopyRoutine', { id: routineId })
  },
  // Two helper methods to minimize the requests made to the databas
  getAllSetListsOfRoutine: async routineId => requestBuilder('getAllSetListsOfRoutine', { routineId }),
  getAllExercisesOfRoutine: async routineId => requestBuilder('getAllExercisesOfRoutine', { routineId }),
  markAllAsDone: async routineId => requestBuilder('markAllSetsAsDone', { routineId }),
  removeExerciseFromRoutine: async (routineId, index) => requestBuilder('removeExerciseFromRoutine', { routineId, index })
}

// Here we compile the object indexed by entityName
const user     = createFunctions('User', 'Users', userFunctions)
const routine  = createFunctions('Routine', 'Routines', routineFunctions)
const history  = createFunctions('History', 'Histories', {})
const exercise = createFunctions('Exercise', 'Exercises', {})
const setList  = createFunctions('SetList', 'SetLists', setListFunctions)

// Login methods
const logIn = (username, password) => Request.setCredentials(username, password)
const logOut = () => Request.unsetCredentials()
const isLoggedIn = async () => Request.isLoggedIn()

// Here are two methods to map arrays asyncronously

// Method to send all request first before resolving (just for speed)
// Ideally this one is not used because it doesn't perform
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
