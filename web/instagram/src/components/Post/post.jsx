import React from "react";
import axios from "axios";



const Comment = ({ data }) => {
  const { id , body ,user } = data;

  return (
      <div className="card">
          <div className="card-body">
                <img   src={user.image} alt={user.image} />
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
        comments: []
      }
    }




    getPost = () => {    //RETURN OBJETO PROMESA DE POST
      const postToSearch = localStorage.getItem("IdPostToShow");
       return axios.get(`http://localhost:7000/post/${postToSearch}`)
         .then(response => response.data)
         .catch(error => Promise.reject(error.response.data))
   }



   addComment(){

   }

   
    getPostData = () => {
       return this.getPost()
                   .then(post => {
                       this.setState({
                           id: post.id,
                           description: post.description,
                           portrait: post.portrait,
                           likes:  post.likes,
                           comments: post.comments
                       }
                       )
                   }                  
                   ).catch(error => this.setState({ error }))  
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
            }
          })
            .catch(err => console.log(err));
    }

   





       renderPost() {
        const { id, description, portrait, user , likes, comments, input} = this.state; //agarro lo que necesito del state

        return (
            <div className= "twoCard"> 
                <div>
                    <img  className="card-img-top" src={portrait} alt={portrait} />
                    <b>{description}</b>
                    <br/><br/>
                    <button onClick={() => {
                      axios.put(`http://localhost:7000/post/${id}/like`); 
                      this.getPostData();
                      } }>
                      Like
                    </button>

                    <b>{likes.length} Likes</b>

                    <br/><br/>

                    <div>
                      {comments.map(comment => <Comment data={comment}/>)}
                    </div> 

                    
                    <div className="col-11">
                                          <input
                                          type="text"
                                          name="New Comment"
                                          value={input}
                                          className="form-control"
                                          ></input>
                    </div>

        
                    <div className="buttonContainer text-right">
                      <button type="button" className="btn btn-primary" onClick={ this.makeAComment(id,input), this.getPostData }>Add Comment</button>
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


