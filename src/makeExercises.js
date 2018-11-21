import Database from '@/database/Database'
import exerciseList from './exerciseList'

const createExercises = () => {
  exerciseList.forEach(exercise => {
    Database.logIn("admin", "admin")
    Database.exercise.saveEntity({
      'name': exercise,
      'description': 'No description',
      'imgurl': 'nourl'
    })
  })
}

export default createExercises
