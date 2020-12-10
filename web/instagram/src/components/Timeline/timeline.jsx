import React from "react";
import axios from "axios";
import api from "../../Api/api"
import { Link } from 'react-router-dom';


const Post = ({ data, makeALike }) => {
    const { id, description, portrait, landscape, date, user, likes } = data;
  

    return (
        <div className="card">
            <div className="card-body">

                <Link to = "/profile">
                    <img onClick={() => {
                        localStorage.setItem("IdUserToShow", user.id);} }  
                        src={user.image} 
                        alt={user.image} />
                </Link>

                <b>{user.name}</b> 

                <Link to = "/post">
                    <img onClick={() => {
                        localStorage.setItem("IdPostToShow", id);} } 
                        className="card-img-top" 
                        src={portrait} 
                        alt={user} />
                </Link>
                
                <div style={{paddingTop:"10px"}}>
                    <b>{description}</b>
                </div>
                    
                <div style={{paddingTop:"20px"}}>
                    <button onClick={() => {
                        makeALike(id);} }
                        > Like
                    </button>  
                                  
                    <b>{likes.length} Likes</b>

                </div>
                    

            </div>
        </div>
    );
}
            

const User = ({ id, name, image }) => {

    return (
            <div className="twoCard" style={{paddingTop:"20px"}}>

                <Link to = "/profile">
                    <img onClick={() => {
                        localStorage.setItem("IdUserToShow", id);} }  
                        src={image} 
                        alt={image} />
                </Link>
                <div style={{paddingTop:"20px"}}>
                    <b>{name}</b>
                </div>
                
            </div>
    );
}



class Timeline extends React.Component {

    constructor(props) {
      super(props)
      this.state = {
        id : "",
        name: "",
        imagenPerfil: "",
        posts: [],
        followers: []
      }
    }

    getUserData = () => {
        return api.getUserLogged()
            .then(usuario => {
                this.setState({
                    id: usuario.id,
                    posts:  usuario.timeline,
                    name: usuario.name,
                    imagenPerfil: usuario.image,
                    followers: usuario.followers
                    })
                    localStorage.setItem("IdUserLogged", usuario.id);
                })
                .catch(error => this.setState({ error }))  
    }

    makeALike = id =>{
        api.makeALike(id)
        .then(() => this.getUserData())
    }

    componentDidMount() {
        this.getUserData()
    }


    renderPosts() {
        const { posts,name,imagenPerfil,followers,id } = this.state; //agarro lo que necesito del state
        
        return (
            <div className= "twoCard"> 
                <div style={{ width: 1600 }}>
                    {
                    posts.length===0?
                    <p><b> NO HAY POSTS :( </b></p> :
                    posts.map(post => <Post data={post} makeALike = {this.makeALike}  />)}
                </div>  

                <div className="card" style={{ width: 600 }}>
                    <div className="card-body">
                        <User name = {name} image = {imagenPerfil} id = {id} />  
                        
                        <div style={{paddingTop:"30px", paddingLeft: "10px"}}>
                            <b>{"Followers"}</b>
                        </div>
                        
                        
                        {followers.length===0?
                            <p> <b> NO HAY FOLLOWERS :(</b></p> :
                             followers.map(follower => <User name = {follower.name} image = {follower.image} id = {follower.id} />)}
                    
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