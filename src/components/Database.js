import Request from "./Request"

// breyta thessum ef madur aetlar ad skipta milli server og localhost
//const baseString = "http://<ipTala>:8080/database/userEnabled/"
const baseString = "http://localhost:8080/database/userEnabled/"

const getById = (entityName, id, idKey) => {
  idKey = idKey || "id"
  const requestString = `${baseString}one${entityName}?${idKey}=${id}`
  return Request.make(requestString)
}

const requestBuilder = (methodName, requestParams) => {
  const method = `${baseString}${methodName}?`
  const params = requestString.keys().map(key => key + "=" + requestParams[key]).join("&")
  return Request.make(method + params)
}

const getAll = (pluralEntityName) => {
  return Request.make(requestStringBuilder('all'+pluralEntityName))
}

const makeNewEntity = (methodName, newEntityParams) => {
  return requestBuilder(methodName, newEntityParams)
}

// ef allir hlutir eru med eitthvad function tha getur madur sett thad hingad
const createFunctions = (entityName, specificFunctions) => {
  const functions = {
    getOne: (id, idkey) => getById(entityName, id, idkey),
    getAll: () => getAll(),
    makeNewEntity: (newEntityParams) => makeNewEntity(entityName, newEntityParams)
    delete:
  }
  // specificFunctins overrides functions in the output object
  return Object.assign(functions, specificFunctions)
}

const filter = (obj, hotelId) => {
  let array = []
  for(let key in obj) {
    const review = obj[key]
    if(review.hotelId == hotelId) array.push(review)
  }
  console.log(array)
  return array
}

const userFunctions = {
  newUser: (name, email, password) => requestBuilder("newUser", { name, email, password })
}

const user = createFunctions("User", userFunctions)
const dataFunctions = {
  user,
  iconURLs
}

let Request = new RequestBuilder()
const setCredentials = (username, password) => {
  Request.setCredentials(username, password)
  return dataFunctions
}

export default { setCredentials }
