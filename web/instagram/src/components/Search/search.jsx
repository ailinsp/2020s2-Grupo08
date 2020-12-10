import React from "react";
import { Link } from 'react-router-dom';
import api from "../../Api/api"


const Post = ({ data }) => {
    const { id, portrait, user} = data;
  
    return (
        <div className="card">
            <div className="card-body">

                <Link to = "/post">
                    <img onClick={() => {
                        localStorage.setItem("IdPostToShow", id);} } 
                        className="card-img-top"
                        src={portrait} 
                        alt={user} />
                </Link>

            </div>
        </div>
    );
}
            

const User = ({ id, name, image }) => {

    return (
            <>
            <div className="twoCard">
                
                <Link to = "/profile">
                    <img onClick={() => {
                        localStorage.setItem("IdUserToShow", id);} }  
                        src={image} 
                        alt={image} />
                </Link>

                <b>{name}</b>
            </div>
            <br></br>
            </>
    );
}
  
class Search extends React.Component {

    constructor(props) {
      super(props)
      this.state = {
          posts: [],
          users: []
      }
    }  
    
    getSearch = () => {
        const search = this.props.location.search
        return api.search(search)
            .then(response => response.data)
            .catch(error => Promise.reject(error.response.data))
    }

    componentDidMount() {
        this.getUsers()
        this.getPosts()
    }


    componentDidUpdate(prevProps) {  //Lo ejecuta cuando el estado se modifica, en este caso los post.
        if (this.props.location.search !== prevProps.location.search) {    
            this.getUsers();
            this.getPosts();
              
        } 
}

    getUsers() {
        return this.getSearch()
        .then( json => {
            this.setState({
                users : json.content})
            })
        .catch(error => this.setState({ error }))
    }   

    getPosts(){
        return this.getSearch()
        .then( json => {
            this.setState({
                posts : json.content})
        })
        .catch(error => this.setState({ error }))
    }   

    renderUsers(){
        const { users } = this.state;
        return (
            <div > 
                <div >
                    {users.map(user => <User name = {user.name} image = {user.image} id = {user.id}  />)}
                </div>  
            </div>
        );
    }

    

   renderPosts(){
    const { posts } = this.state;

    return (

        <div className="container-fluid">
            <br></br>
            <br></br>
                <div className="row">
                    {posts.map(post => (
                        <div className="col-md-4 col-sm-12">
                            <div>
                            <Post data={post}/>
                            </div>

                        </div>
                    ))}
                </div>
            </div>

    );


   }
    
    
    render(){
        const condition = this.props.location.search.includes("%23")
        let result;
        
        if(condition){
            result = <div className="container"> {this.renderPosts()} </div>
        } else {
            result = <div className="container"> {this.renderUsers()} </div>
        } 
        return (
            <div>
              {result}
            </div>
          );
        ;
      } 
} 

export default Search;