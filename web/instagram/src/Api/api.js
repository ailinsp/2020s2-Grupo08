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

   


export default {getUserLogged,getUser}