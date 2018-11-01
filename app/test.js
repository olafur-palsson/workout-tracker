

import DataController from "./DataController"


// username and password as arguments


const test = async () => {
  let dataController = DataController.setCredentials()
  let response = await dataController.user.newUser("oli","oli","oli")
  console.log(response)
  dataController = DataController.setCredentials("oli", "oli")
  response = await dataController.user.getOne("oli")
  console.log(response)
  response = await dataController.user.newUser("blaengur", "clang","blegh")
  console.log(response)
}

export default test
