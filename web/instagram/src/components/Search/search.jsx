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
  
class Search extends React.Component {

    constructor(props) {
      super(props)
      this.state = {
          posts: [],
          users: []
      }
    }  
    
    getSearch = () => {
        const search = localStorage.getItem("searchValue")
        console.log(search)
        return axios.get(`http://localhost:7000/search?q=${search} `)
            .then(response => response.data)
            .catch(error => Promise.reject(error.response.data))
    }

    componentDidMount() {
        this.getUsers()

    }

    getUsers() {
        return this.getSearch().then( json => {
            this.setState({
                users : json.content
            })
        }                  
        ).catch(error => this.setState({ error }))
    }   

    getPosts(){
        return this.getSearch().then( json => {
            this.setState({
                posts : json.content
            })
        }                  
        ).catch(error => this.setState({ error }))
    }   

    renderUsers(){
        const { users } = this.state;
        return (
            <div className= "twoCard"> 
                <div style={{ width: 1600 }}>
                    {users.map(user => <User name = {user.name} image = {user.image} id = {user.id}  />)}
                </div>  

            </div>
        );
    }

    

   // renderPosts(){}
    
    
    render(){
    //    if (es post (hash))
    //        render post
    //    else
    //        render user

        return (
            <div className="container">
                {this.renderUsers()}
            </div>
        );
      } 
} 

export default Search;