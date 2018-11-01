import RequestBuilder from "./Request"

let Request = new RequestBuilder()
const setCredentials = (username, password) => {
	Request.setCredentials(username, password)
	return dataFunctions
}


// breyta thessum ef madur aetlar ad skipta milli server og localhost
//const baseString = "http://<ipTala>:8080/database/userEnabled/"
const baseString = "http://localhost:8080/database/userEnabled/"

const getById = (entityName, id, idKey) => {
	idKey = idKey || "id"
	const requestString = `${baseString}one${entityName}?${idKey}=${id}`
	return Request.make(requestString)
}

const getAllByType = (pluralEntityName) => {
	let requestString = `${baseString}all${pluralEntityName}`
	return Request.make(requestString)
}

const newUser = (name, email, password) => {
	let requestString = `${baseString}newUser?`
	requestString += `name=${name}&`
	requestString += `email=${email}&`
	requestString += `password=${password}`
	return Request.make(requestString)
}

const user = {
	newUser: (name, email, password) => newUser(name, email, password),
	getOne: (id) => getById("User", id, "email"),
	getAll: () => getAllByType("Users"),
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


const iconURLs ={
		wifi: "https://i.imgur.com/QOLIhxO.png",
		shower: "https://i.imgur.com/y1x2e1b.png",
		breakfast: "https://i.imgur.com/PzrJ9Aw.png",
		private_bathroom: "https://i.imgur.com/SGeRQXq.jpg",
		tea: "https://i.imgur.com/nvKGfIv.png",
		coffee: "https://i.imgur.com/4edoUkq.png",
		swimming_pool: "https://i.imgur.com/9N2dMZv.png",
		accessible: "https://i.imgur.com/ax7VzhD.png",
		linen: "https://i.imgur.com/7tC9XYv.png"
}

const dataFunctions = {
	user,
	iconURLs
}



export default { setCredentials }
