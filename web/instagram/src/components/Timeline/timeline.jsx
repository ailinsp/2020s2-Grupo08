import React from "react";
import axios from "axios";

//contiene name, image, followers, timeline
const getLoggedUser = () => {
    return axios.get('http://localhost:7000/user')
      .then(response => response.data)
      .catch(error => Promise.reject(error.response.data))
}

const getTimeline = () => {
    //getLoggedUser().???
}

const getPosts = () => {
    //getTimeline().????
}

const Post = ({ data }) => {
    const { id, description, portrait, landscape, likes, date, user } = data;
  
    return (
        <div>
            <img src={landscape} alt={user} />
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
        getPosts()
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