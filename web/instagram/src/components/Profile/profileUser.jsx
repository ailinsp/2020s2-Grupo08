import React from "react";
import axios from "axios";


//contiene name, image, followers, timeline


const Post = ({ data }) => {
    const { id, description, portrait, landscape, likes, date, user } = data;
  
    return (
        <div className="card">
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
        followers: [], //Estos son los followers del userLogeado
        toggleFollow: true,
        isFollowing: false
      }
    }

     logout(){
        localStorage.removeItem("token");
        localStorage.removeItem("IdUserLogged")
        localStorage.removeItem("IdUserToShow")
        localStorage.removeItem("IdPostToShow")
        window.location.href='http://localhost:3000/'
      };

      async getUser() {
        const usuarioABuscar = localStorage.getItem("IdUserToShow");
        return await axios.get(`http://localhost:7000/user/${usuarioABuscar}`)
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
                        })
                    }                  
                    ).catch(error => this.setState({ error }))  
    }


    async getUserLogged() {
        return await axios.get(`http://localhost:7000/user`)
            .then(response => response.data)
            .catch(error => Promise.reject(error.response.data))
                                                                 
     }


      getUserLoggedData = () => {
        return this.getUserLogged()
        .then(usuario => {
            this.setState({
               followers:  usuario.followers
                            })}).catch(error => this.setState({ error }))  
    }


    isFollowing= (followers,id) =>{
        this.setState({
        isFollowing: 
        followers.find(followers => followers.id === id)}
                                    )
                                }



     componentDidMount()  {
        
        const { id, followers, posts, isFollowing } = this.state;
        this.getUserData();
        this.getUserLoggedData();
        this.isFollowing(followers,id)
        

        // const  isFollowing = followers.find(followers => console.log("followers.id", followers.id, "id", id) || followers.id === id)

        
        console.log("FOLLOWERS:", followers)
        console.log("POSTS:", posts)
        console.log("ID:", id)
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

        const {name,image,id} = this.state;

        return (

        

            <div className="container">
                <img src={image} alt={image} />
                <b>{name}</b>


                {localStorage.getItem("IdUserLogged") !== id ? (
                    <button onClick={() => {
                        axios.put(`http://localhost:7000/user/${id}/follow`);  
                        this.getUserData();
                        this.setState({toggleFollow: !this.state.toggleFollow})
                        } }>
                       {this.state.toggleFollow? "UnFollow":"Follow"}
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