
import Database from './database/Database'

const newUserTest = async () => {
  let database = Database.setCredentials()
  return response = await database.user.newUser('oli', 'oli', 'oli')
}

const newUserRepsonse = await newUserTest()
test('Add new user with name, email & password as "oli"', () => {
  expect(newUserRepsonse).toBe('oli')
})

const getOneWithCredentials = async () => {
  database = Database.setCredentials('oli', 'oli')
  response = await database.user.getOne('oli')
}

const getOneResponse = await getOneWithCredentials()
test('Add new user with name, email & password as "oli"', () => {
  expect(typeof getOneResponse).toBe('object')
})

export default test
