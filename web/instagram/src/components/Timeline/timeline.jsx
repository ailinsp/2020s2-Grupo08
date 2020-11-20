import React from "react";
import axios from "axios";
import api from "../../Api/api"



const Post = ({ data, makeALike }) => {
    const { id, description, portrait, landscape, date, user, likes } = data;
  



    return (
        <div className="card">
            <div className="card-body">

                <img onClick={() => {
                    localStorage.setItem("IdUserToShow", user.id);
                    window.location.href='http://localhost:3000/profile'} }  
                    src={user.image} 
                    alt={user.image} />

                <b>{user.name}</b> 
                <br/><br/>

                <img onClick={() => {
                    localStorage.setItem("IdPostToShow", id);
                    window.location.href=`http://localhost:3000/post`} } 
                    className="card-img-top" 
                    src={portrait} 
                    alt={user} />

                <b>{description}</b>
                <br/><br/>

                <button onClick={() => {
                    makeALike(id);} }
                    > Like
                </button>

                <b>{likes.length} Likes</b>
            </div>
        </div>
    );
}
            

const User = ({ id, name, image }) => {

    return (
            <>
            <div className="twoCard">

                <img onClick={() => {
                    localStorage.setItem("IdUserToShow", id);
                    window.location.href='http://localhost:3000/profile'} }  
                    src={image} 
                    alt={image} />

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
        axios.put(`http://localhost:7000/post/${id}/like`).then(() => this.getUserData())
        
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
                <div style={{ width: 1600 }}>
                    {
                    posts.length===0?
                    <p><b> <br/><br/><br/><br/>  NO HAY POSTS :( </b> </p> :
                    posts.map(post => <Post data={post} makeALike = {this.makeALike}  />)}
                </div>  

                <div className="card" style={{ width: 600 }}>
                    <div className="card-body">
                        <User name = {name} image = {imagenPerfil} id = {id} />  
                        <br/><br/>

                        <b>{"Followers"}</b>
                        <br/><br/>
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