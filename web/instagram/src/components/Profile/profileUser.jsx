import React from "react";
import axios from "axios";


//contiene name, image, followers, timeline


const Post = ({ data }) => {
    const { id, description, portrait, landscape, likes, date, user } = data;
  
    return (
        <div classclassName="card">
            <div className="card-body">
                <br></br>


                <img onClick={() => {
                    localStorage.setItem("IdPostToShow", id);
                    window.location.href=`http://localhost:3000/post`
                    } } className="card-img-top" src={portrait} alt={user} />






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
        id: "",
        followers: [],
        toggleFollow: true
      }
    }

     logout(){
        localStorage.removeItem("token");
        localStorage.removeItem("IdUserLogged")
        localStorage.removeItem("IdUserToShow")
        localStorage.removeItem("IdPostToShow")
        window.location.href='http://localhost:3000/'
      };

    getUser() {
        const usuarioABuscar = localStorage.getItem("IdUserToShow");
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
                            id: usuario.id,
                            followers: usuario.followers
                        })
                    }                  
                    ).catch(error => this.setState({ error }))  
    }
    


    componentDidMount() {
        this.getUserData()
        const isFollowing = this.state.followers.find(followers => followers.id == localStorage.getItem("idUserLogged"))
        this.setState({toggleFollow : isFollowing})
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


                {localStorage.getItem("IdUserLogged") != id ? (
                    <button onClick={() => {
                        axios.put(`http://localhost:7000/user/${id}/follow`);  
                        this.getUserData();
                        this.setState({toggleFollow: !this.state.toggleFollow})
                        } }>
                       {this.state.toggleFollow? "Follow":"Unfollow"}
                    </button>
                ):( //else
                    <button type="button" onClick={this.logout}>
                    Logout
                    </button>
                )}




                <br/><br/>
                <br/><br/>

                {this.renderPosts()}
            </div>
        );
      }
    }

export default ProfileUser;