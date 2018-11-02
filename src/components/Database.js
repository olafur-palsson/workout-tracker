import RequestFactory from './Request'
let Request = new RequestFactory()

// breyta thessum ef madur aetlar ad skipta milli server og localhost
// const baseString = "http://<ipTala>:8080/database/userEnabled/"
const baseString = 'http://localhost:8080/database/userEnabled/'

const requestBuilder = (methodName, requestParams) => {
  const method = `${baseString}${methodName}?`
  const params = Object.keys(requestParams).map(key => key + '=' + requestParams[key]).join('&')
  return Request.make(method + params)
}

const getById = (singularEntityName, id, idKey) => {
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
    getOne: (id, idkey) => getById(singularEntityName, id, idkey),
    getAll: () => getAll(pluralEntityName),
    makeNewEntity: (newEntityParams) => makeNewEntity(singularEntityName, newEntityParams)
  }
  // specificFunctins overrides functions in the output object
  return Object.assign(functions, specificFunctions)
}

const userFunctions = {
  newUser: (name, email, password) => requestBuilder('newUser', { name, email, password })
}

const user = createFunctions('User', 'Users', userFunctions)
const routine = createFunctions('Routine', 'Routines', {})
const dataFunctions = {
  routine,
  user
}

const setCredentials = (username, password) => {
  Request.setCredentials(username, password)
  return dataFunctions
}

export default { setCredentials }
