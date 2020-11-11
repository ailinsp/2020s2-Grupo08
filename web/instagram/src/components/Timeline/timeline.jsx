import React, { Component }  from "react";
import axios from "axios";
import Post from './components/Post/post.jsx';

class Timeline extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
    }
  }

  componentDidMount(){
      axios
        .get("http://localhost:7000/user")   
        .then(response => response.data).then(posts => this.setState({ posts }))
  }

  renderPosts() {
    const { posts } = this.state;
    if(posts.length === 0) {
      return <div>Loading!!</div>
    }
    return (
      <div>
        {posts.map(post => <Post data={post} />)}
      </div>
    );
  }

  render() {
    const { name } = this.user || {};
    
    return (
      <div className="container">
        <h1>Hello {name}!!</h1>
        {this.renderPosts()}
      </div>
    );
  }
}

export default Timeline;


