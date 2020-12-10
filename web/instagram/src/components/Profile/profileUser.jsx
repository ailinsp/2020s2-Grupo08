import React from "react";
import api from "../../Api/api"
import { Link } from 'react-router-dom';


//contiene name, image, followers, timeline

const Post = ({ data }) => {
    const { id, portrait, user } = data;
  
    return (
        <div className="card">
            <div className="card-body">
                <Link to="/post">
                    <img onClick={() => {
                        localStorage.setItem("IdPostToShow", id);
                        } } 
                        className="card-img-top" 
                        src={portrait} 
                        alt={user} />
                </Link>
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

      
    getUserData = () => {
        return api.getUser()
                    .then(usuario => {
                        this.setState({
                            posts:  usuario.posts,
                            name: usuario.name,
                            image : usuario.image,
                            id: usuario.id,
                        })})
                    .catch(error => this.setState({ error }))  
    }
    

    isFollowing= (followers,id) =>{
        this.setState({
            isFollowing: followers.find(followers => followers.id === id)
        })
    }


    async componentDidMount()  {
        
        const { id, followers, posts, isFollowing } = this.state;

        Promise.all([api.getUser(),api.getUserLogged() ]).then((values) => { 
            const user = values[0]
            const userLogged = values[1]
            
            this.setState({
                posts:  user.posts,
                name: user.name,
                image : user.image,
                id: user.id,
                followers: userLogged.followers
            })
            this.isFollowing(userLogged.followers,user.id)
        })

        // const  isFollowing = followers.find(followers => console.log("followers.id", followers.id, "id", id) || followers.id === id)
    }
  
    renderPosts() {
        const { posts } = this.state;
        
        return (
            <div className="container">
                <div className="row">
                    {
                    posts.length===0?
                    <p><b> NO HAY POSTS :( </b> </p> :
                    posts.map(post => (
                        <div className="col-md-4 col-sm-12" >
                            <div>
                            <Post data={post}/>
                            </div>


                        </div>
                    ))}
                </div>
            </div>
        );
    }

    logout(){
        localStorage.removeItem("token");
        localStorage.removeItem("IdUserLogged")
        localStorage.removeItem("IdUserToShow")
        localStorage.removeItem("IdPostToShow")
    }

    render() {

        const {name,image,id} = this.state;

        return (
            <div className="container" style={{paddingTop:"10px"}} >

                <div style={{padding:"20px"}} >
                    <img src={image} alt={image} />
                    <b>{name}</b>
                

                    {localStorage.getItem("IdUserLogged") !== id ? (
                        <button className="btn btn-sm btn-outline-secondary" type="button" onClick={() => {
                            api.follow(id)
                                .then(
                                    this.getUserData(),
                                    this.setState({isFollowing: !this.state.isFollowing}))}}>
                            {this.state.isFollowing? "UnFollow":"Follow"}
                        </button>
                    ):(  
                        <Link to = "/">
                            <button className="btn btn-sm btn-outline-secondary" type="button" onClick={this.logout}>
                                Logout
                            </button>
                        </Link>
                    )}
                </div>

                {this.renderPosts()}
            </div>
        );
    }
}

export default ProfileUser;