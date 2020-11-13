import React from "react";
import axios from "axios";
import { useParams } from 'react-router-dom';

//contiene name, image, followers, timeline
const getLoggedUser = () => {    //RETURN OBJETO PROMESA DE USUARIO
    return axios.get(`http://localhost:7000/user`)
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
                <img class="card-img-top" src={portrait} alt={user} />
                <b>{description}</b>
                <br/><br/>
                <button onClick={() => axios.put(`http://localhost:7000/post/${id}/like`)}>
                   Like
                </button>
                <b>{likes.length} Likes</b>    
    
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

    doLikeAPost(id) {   //RETURN OBJETO PROMESA DE USUARIO
    
      axios.put('http://localhost:7000/post/${id}/like')
      .catch(error => Promise.reject(error.response.data))
  }

    componentDidMount() {
        this.doLikeAPost(this.props.match.params.id)
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