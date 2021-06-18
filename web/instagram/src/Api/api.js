import axios from "axios";

async function getUserLogged(){
    try{
         const response = await axios.get(`http://localhost:7000/user`)
         return(response.data)
    }catch(error){Promise.reject(error.response.data)}
}

async function getUser() {
    try{
        const usuarioABuscar = localStorage.getItem("IdUserToShow");
        const response = await axios.get(`http://localhost:7000/user/${usuarioABuscar}`)
        return(response.data)
   }catch(error){Promise.reject(error.response.data)}
}

async function getPostById() {
    try{
        const postToSearch = localStorage.getItem("IdPostToShow");
        const response = await axios.get(`http://localhost:7000/post/${postToSearch}`)
        return(response.data)
    }catch(error){Promise.reject(error.response.data)}
}

async function makeALike(id) {
    try{
        const response = await  axios.put(`http://localhost:7000/post/${id}/like`)
        return(response.data)
    }catch(error){Promise.reject(error.response.data)}
}

async function login(data) {
    try{
        const response = await axios.post("http://localhost:7000/login", data)
        return(response)
    }catch(error){Promise.reject(error.response.data)}
}

async function makeAComment(id,bodyRequest) {
    try{
        const response = await axios({  method: 'post',
                                        url: `http://localhost:7000/post/${id}/comment`,
                                        data: {
                                        body: bodyRequest
                                        }})
        return(response.data)
    }catch(error){Promise.reject(error.response.data)}
}

async function follow(id) {
    try{
        const response = await axios.put(`http://localhost:7000/user/${id}/follow`)
        return(response.data)
    }catch(error){Promise.reject(error.response.data)}
}

async function register(data) {
    try{
        const response = await axios.post("http://localhost:7000/register", data)  
        return(response)
    }catch(error){Promise.reject(error.response.data)}
}

async function search(item) {
    try{
        const response = await axios.get(`http://localhost:7000/search${item}`)
        return(response)
    }catch(error){Promise.reject(error.response.data)}
}



export default {getUserLogged,getUser,getPostById, makeALike, 
    login, makeAComment, makeALike, follow, register, search}