import React from "react";
import axios from "axios";


const Post = ({ data, getUserData }) => {
    const { id, description, portrait, landscape, date, user, likes } = data;
  
    return (
        <div className="card">
            <div className="card-body">


                <img onClick={() => {
                    localStorage.setItem("IdUserToShow", user.id);
                    window.location.href='http://localhost:3000/profile'
                    } }  src={user.image} alt={user.image} />


                <b>{user.name}</b> 
                <br/><br/>

                <img onClick={() => {
                    localStorage.setItem("IdPostToShow", id);
                    window.location.href=`http://localhost:3000/post`
                    } } className="card-img-top" src={portrait} alt={user} />


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
            

const User = ({ id, name, image }) => {

    return (
            <>
            <div className="twoCard">


                <img onClick={() => {
                    localStorage.setItem("IdUserToShow", id);
                    window.location.href='http://localhost:3000/profile'
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
        id : "",
        name: "",
        imagenPerfil: "",
        posts: [],
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
                        id: usuario.id,
                        posts:  usuario.timeline,
                        name: usuario.name,
                        imagenPerfil: usuario.image,
                        followers: usuario.followers
                    }
                    )
                    localStorage.setItem("IdUserLogged", usuario.id);
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
                <div style={{ width: 1600 }}>
                    {posts.map(post => <Post data={post} getUserData = {this.getUserData} />)}
                </div>  

                <div className="card" style={{ width: 600 }}>
                    <div className="card-body">
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