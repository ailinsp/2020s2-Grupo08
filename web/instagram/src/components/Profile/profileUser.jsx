import React from "react";
import axios from "axios";

//contiene name, image, followers, timeline


const Post = ({ data }) => {
    const { id, description, portrait, landscape, likes, date, user } = data;
  
    return (
        <div class="card">
            <div class="card-body">
                <br></br>
                <img class="card-img-top" src={portrait} alt={user} />
            </div>
        </div>
    );
}

class ProfileUser extends React.Component {

    constructor(props) {
      super(props)
      this.state = {
        posts: [],
        name: "",
        image: "",
        id: ""
      }
    }

    getUser() {
        const usuarioABuscar = localStorage.getItem("IdUser");
        return axios.get(`http://localhost:7000/user/${usuarioABuscar}`)
            .then(response => response.data)
            .catch(error => Promise.reject(error.response.data));
    }
    
    
    
    getUserData = () => {
        return this.getUser()
                    .then(usuario => {
                        this.setState({
                            posts:  usuario.posts,
                            name: usuario.name,
                            image : usuario.image,
                            id: usuario.id
                        })
                    }                  
                    ).catch(error => this.setState({ error }))  
    }
    


    componentDidMount() {
        this.getUserData()
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

        const {name,image,id } = this.state;

        return (

        

            <div className="container">
                <img src={image} alt={image} />
                <b>{name}</b>
                <button onClick={() => {
                    axios.put(`http://localhost:7000/user/${id}/follow`);  
                    this.getUserData();
                    } }>
                   Follow
                </button>

                <br/><br/>
                <br/><br/>

                {this.renderPosts()}
            </div>
        );
      }
    }

export default ProfileUser;