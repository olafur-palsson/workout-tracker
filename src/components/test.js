
import Database from './Database'

const test = async () => {
  let database = Database.setCredentials()
  let response = await database.user.newUser('oli', 'oli', 'oli')
  console.log(response)
  database = Database.setCredentials('oli', 'oli')
  response = await database.user.getOne('oli')
  console.log(response)
  response = await database.user.newUser('blaengur', 'clang', 'blegh')
  console.log(response)
}

export default test
