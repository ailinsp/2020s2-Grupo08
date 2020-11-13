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

    const likesT = likes.lenght;
  
    return (
        <div>

          <br/><br/>
          <img src={user.image} alt={user} />
          <b>{user.name}</b> 
          <br/><br/>
          <img src={landscape} alt={user} />
          <b>{description}</b>
          <br/><br/>
          {likesT}
           
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