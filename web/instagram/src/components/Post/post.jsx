import React from "react";
import axios from "axios";
import api from "../../Api/api"


const Comment = ({ data }) => {
  const { id , body ,user } = data;

  return (
    <div className="card">
      <div className="card-body">
        
        <img onClick={() => {
          localStorage.setItem("IdUserToShow", user.id);
          window.location.href='http://localhost:3000/profile'
          } }  src={user.image} alt={user.image} />
                    
          <b>{user.name}:   </b>
          <b>{body}</b>
        
      </div>
    </div>
  );
}


class Post extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      id: "",
      description: "",
      portrait: "",
      likes: [],
      comments: [],
      value : ""
    }
  }

  getPostData = () => {
    return api.getPostById()
              .then(post => {
                    this.setState({
                    id: post.id,
                    description: post.description,
                    portrait: post.portrait,
                    likes: post.likes,
                    comments: post.comments
                    })})
              .catch(error => this.setState({ error }))  
  }
   
  componentDidMount() {
    this.getPostData()
  }

  makeAComment(id,bodyRequest) {
    axios({
          method: 'post',
          url: `http://localhost:7000/post/${id}/comment`,
          data: {
          body: bodyRequest
          }})
          .then(() => {
            this.getPostData()
            this.setState({value: ""})
          })
          .catch(err => console.log(err));
  }

  makeALike(id) {
    axios({
          method: 'put',
          url: `http://localhost:7000/post/${id}/like`
          })
          .then(() => {
            this.getPostData()
          })
          .catch(err => console.log(err));
  }

  onChange = e =>  {
    this.setState({value: e.target.value});
  }

  submitComment(id){
    this.makeAComment(id,this.state.value);
  }

  renderPost() {
    const { id, description, portrait, user , likes, comments} = this.state; //agarro lo que necesito del state
      
    return (
      <div className= "twoCard"> 
        <div>
          
          <img  className="card-img-top" src={portrait} alt={portrait} />
          
          <b>{description}</b>
          <br/><br/>
          
          <button onClick={() => this.makeALike(id) }>
              Like
          </button>

          <b>{likes.length} Likes</b>
          <br/><br/>

          <div>
            {comments.map(comment => <Comment data={comment}/>)}
          </div> 


          <div className = "form-group">
            <input 
              type = "text" 
              className ="form-control" 
              placeholder="Add Comment" 
              value={this.state.value}
              onChange = {this.onChange} >
            </input>
          </div>
        
          <div className="buttonContainer text-right">
            <button type="text" 
                    className="btn btn-primary" 
                    disabled = {this.state.value === ""} 
                    onClick={()=>this.submitComment(id)}>
                    Add Comment
            </button>
          </div>  

                  
        </div>
      </div>
    );
  }
  
  render() {
    return (
      <div className="container">
        {this.renderPost()}
      </div>
    );
  }


}

export default Post;


