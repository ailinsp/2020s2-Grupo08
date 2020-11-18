import React from "react";
import "./Header.css";
import axios from "axios";

  
class Search extends React.Component {

    constructor(props) {
      super(props)
      this.state = {
        searchValue : ""
      }
    }  
    
    getSearch = () => {
        return axios.get(`http://localhost:7000/search`)
            .then(response => response.data)
            .catch(error => Promise.reject(error.response.data))
    }

    render() {
        return true
    }   
} 

export default Search;