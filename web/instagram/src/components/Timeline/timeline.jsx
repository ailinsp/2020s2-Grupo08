import React from "react";
import axios from "axios";

//contiene name, image, followers, timeline
const getLoggedUser = () => {    //RETURN OBJETO PROMESA DE USUARIO
    return axios.get('http://localhost:7000/user')
      .then(response => response.data)
      .catch(error => Promise.reject(error.response.data))
}

const getTimeline = () => {
    return getLoggedUser().then(usuario => usuario.timeline)  
}




const Post = ({ data }) => {
    const { id, description, portrait, landscape, likes, date, user } = data;
  
    return (
        <div class="card">
            <div class="card-body">
                <img src={user.image} alt={user} />
                <b>{user.name}</b> 
                <br/><br/>
                <img class="card-img-top" src={landscape} alt={user} />
                <b>{description}</b>
                <br/><br/>
                <svg onClick width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
                </svg>  
                <b> {likes.length} Likes </b>    
    
            </div>
        </div>
    );
}

class Timeline extends React.Component {

    constructor(props) {
      super(props)
      this.state = {
        posts: []
      }
    }

    componentDidMount() {
        getTimeline()
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

export default Timeline;