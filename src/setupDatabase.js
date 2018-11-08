
import Database from '@/database/Database'

const squat = {
  'name': 'Squat',
  'description': 'This is long heotuaheotunhteoabs ueoa oe aueboa bkabo kebakb',
  'imgURL': null
}

const curl = {
  'name': 'Bicep curl',
  'description': 'This is long heotuaheotunhteoabs ueoa oe aueboa bkabo kebakb',
  'imgURL': null
}

const dead = {
  'name': 'Deadlift',
  'description': 'This is long heotuaheotunhteoabs ueoa oe aueboa bkabo kebakb',
  'imgURL': null
}

const setup = async () => {
  Database.logOut()
  await Database.user.newUser('oli', 'oli', 'oli')
  await Database.logIn('oli', 'oli')
  const maybeSquat = await Database.exercise.getOne(1)
  console.log(maybeSquat.name)
  if (maybeSquat.name === 'Squat') return

  await Database.exercise.saveEntity(squat)
  Database.exercise.saveEntity(dead)
  Database.exercise.saveEntity(curl)
}

export default setup
