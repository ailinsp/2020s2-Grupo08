import React from "react";
import axios from "axios";

//contiene name, image, followers, timeline
const getLoggedUser = () => {    //RETURN OBJETO PROMESA DE USUARIO
    return axios.get(`http://localhost:7000/user/u_1`)
      .then(response => response.data)
      .catch(error => Promise.reject(error.response.data))
}

const getPostsById = () => {
    return getLoggedUser().then(usuario => usuario.posts)  
}

const Post = ({ data }) => {
    const { id, description, portrait, landscape, likes, date, user } = data;
  
    return (
        <div class="card">
            <div class="card-body">
                <br></br>
                <b>{user.name}</b>
                <img class="card-img-top" src={portrait} alt={user} />
                    
            </div>
        </div>
    );
}

class ProfileUserLogged extends React.Component {

    constructor(props) {
      super(props)
      this.state = {
        posts: []
      }
    }


    componentDidMount() {
        getPostsById()
          .then(posts => this.setState({ posts }))
          .catch(error => this.setState({ error }))
    }
  
    renderPosts() {
        const { posts } = this.state;
        
        return (
            <div>
                {posts.map(post => <Post data={post} />)}
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

export default ProfileUserLogged;