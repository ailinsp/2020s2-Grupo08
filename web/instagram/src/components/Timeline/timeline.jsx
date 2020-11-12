import React, {useState, useEffect} from "react";
import axios from "axios";

function Posts() {
  const [posts, setPosts] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:7000/user`, 
          {
          headers: {
            'Authorization': `token ${access_token}`
          }
            .then(res => setPosts(res.data))
            .catch(err => console.error(err))
    }, []);

    return (
        <div style={{width: '90vw'}}>
            <div> Timeline </div>
            <div>
                {posts.map((post, indx) => (
                    post(indx, post)
                ))
                }
            </div>
        </div>
    )

    function post(indx, post) {
        return <div title={post.description}> </div>;
    }
}

function Timeline() {
  return(
    <Posts/>
  )
  

}

export default Timeline;