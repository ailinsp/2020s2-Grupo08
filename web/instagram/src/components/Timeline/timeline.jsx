import React from "react";
import axios from "axios";
import { Link } from 'react-router-dom'



const Post = ({ data, getUserData }) => {
    const { id, description, portrait, landscape, date, user, likes } = data;
  
    return (
        <div class="card">
            <div class="card-body">
                <img src={user.image} alt={user.name} />
                <b>{user.name}</b> 
                <br/><br/>
                <img class="card-img-top" src={portrait} alt={user} />
                <b>{description}</b>
                <br/><br/>
                <button onClick={() => {
                    axios.put(`http://localhost:7000/post/${id}/like`); 
                    getUserData();
                    } }>
                   Like
                </button>
                <b>{likes.length} Likes</b>
            </div>
        </div>
    );
}
            //window.location.href='/http://localhost:3000/profile'
            //<Link to="/http://localhost:3000/profile" className="btn btn-primary">Profile</Link>

const User = ({ id, name, image }) => {

    return (
            <>
            <div className="twoCard">

                <img onClick={() => {
                    localStorage.setItem("IdUser", id);
                    window.location.href='/http://localhost:3000/profile'
                    } }  src={image} alt={image} />

                <b>{name}</b>
            </div>
            <br></br>
            </>
    );
}



class Timeline extends React.Component {

    constructor(props) {
      super(props)
      this.state = {
        posts: [],
        name: "",
        imagenPerfil: "",
        followers: []
      }
    }

//contiene name, image, followers, timeline
 getLoggedUser = () => {    //RETURN OBJETO PROMESA DE USUARIO
    return axios.get(`http://localhost:7000/user`)
      .then(response => response.data)
      .catch(error => Promise.reject(error.response.data))
}

 getUserData = () => {
    return this.getLoggedUser()
                .then(usuario => {
                    this.setState({
                        posts:  usuario.timeline,
                        name: usuario.name,
                        imagenPerfil: usuario.image,
                        followers: usuario.followers
                    })
                
                }                  
                ).catch(error => this.setState({ error }))  
}

  


    componentDidMount() {
        this.getUserData()
    }


    /*
    componentDidUpdate(prevState) {  //Lo ejecuta cuando el estado se modifica, en este caso los post.
            if (this.state.posts !== prevState.posts) {    
                  this.getUserData(); 
                  
            } 
    }
    */


  
    renderPosts() {
        const { posts,name,imagenPerfil,followers,id } = this.state; //agarro lo que necesito del state
        
        return (
            <div className= "twoCard"> 
                <div>
                    {posts.map(post => <Post data={post} getUserData = {this.getUserData} />)}
                </div>  

                <div class="card" style={{ width: 400 }}>
                    <div class="card-body">
                        <User name = {name} image = {imagenPerfil} id = {id} />  
                        <br/><br/>

                        <b>{"Followers"}</b>
                        <br/><br/>
                        {followers.map(follower => <User name = {follower.name} image = {follower.image} id = {follower.id} />)}
                    </div>
                </div>  
                

            </div>
        );
      }
    
    render() {
        return (
            <div className="container">
                {this.renderPosts()}
            </div>
        );
      }
    }

export default Timeline;