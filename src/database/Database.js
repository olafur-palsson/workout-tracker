import RequestFactory from './Request'
import Cookies from './Cookiehandler'

let Request = new RequestFactory()

// breyta thessum ef madur aetlar ad skipta milli server og localhost
// const baseString = "http://<ipTala>:8080/database/userEnabled/"

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

// ef allir hlutir eru med eitthvad function tha getur madur sett thad hingad
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

// Here is a specific function for 'user'
const userFunctions = {
  newUser: async (name, email, password) => {
    return Request.make(`${newUserMethod}?name=${name}&email=${email}&password=${password}`)
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

const arrayToParameters = (parameterName, arrayOfElements) => {
  return arrayOfElements.reduce((encoding, element) => {
    return encoding + `${parameterName}=${element}`
  }, '')
}

const setListFunctions = {
  saveEntity: async (setList) => {
    // encode parameters
    console.log(setList)
    let parameters = ''
    console.log(setList.listOfWeights)
    parameters += arrayToParameters('weight', setList.listOfWeights)
    console.log(2)
    parameters += arrayToParameters('reps', setList.listOfReps)
    console.log(3)
    parameters += arrayToParameters('setIsDone', setList.finishedSets)

    if (setList.id) {
      parameters += `&id=${setList.id}`
    }
    parameters = parameters.substring(1, parameters.length)
    return saveEntity('SetList', parameters)
  }
}

const routineFunctions = {
  saveEntity: async (exerciseIds, setListIds) => {
    let parameters
    parameters = exerciseIds.reduce((encoding, id) => encoding + `&exerciseIds=${id}`, '')
    parameters = setListIds.reduce((encoding, id) => encoding + `&setListIds=${id}`, parameters)
    return saveEntity('Routine', parameters)
  },
  deepCopyRoutine: async routineId => {
    const id = routineId
    return requestBuilder('deepCopyRoutine', { id })
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
