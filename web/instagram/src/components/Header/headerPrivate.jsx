import React from "react";
import "./Header.css";
import api from "../../Api/api"
import PropTypes from "prop-types";
import { withRouter } from 'react-router'
import { Link } from 'react-router-dom';


class HeaderPrivate extends React.Component {

    constructor(props) {
      super(props)
      this.state = {
        searchValue : "",
        image: "",
        id: "" 
      }
      
    } 
    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };

    getUserData= () => {
        return api.getUserLogged()
                .then(usuario => {
                    this.setState({
                        name: usuario.name,
                        image : usuario.image,
                        id: usuario.id,
                    })})
                .catch(error => this.setState({ error }))
    }

    onChange = e =>  {
        this.setState({searchValue: e.target.value});
    }

    componentDidMount(){
        this.getUserData()
    }


    logout(){
        localStorage.removeItem("token");
        localStorage.removeItem("IdUserLogged")
        localStorage.removeItem("IdUserToShow")
        localStorage.removeItem("IdPostToShow")
    }

    handleChange = (event) => {
        event.preventDefault()
        this.props.history.push(`/search?q=${this.state.searchValue.replace("#", "%23")}`)
        this.setState({searchValue: ""})
    } 

    render() {
        const {searchValue, id, image} = this.state;
        

        return(
            <nav class="navbar navbar-light justify-content-between" >

                <Link className="Nav-brand-logo" to="/timeline">Instagram </Link>

                <form class="form-inline">
                    <input  type = "text" 
                            className ="form-control mr-sm-2" 
                            placeholder="Search" 
                            value={searchValue}
                            onChange = {this.onChange}>
                    </input>

                    <button type="text" 
                            className="btn btn-sm btn-outline-secondary" 
                            disabled = {searchValue === ""} 
                            onClick= {this.handleChange}>
                            Search
                    </button>
                </form>
                
                <Link to = "/profile">
                    <img class="rounded-circle Image-style" onClick={() => {
                        localStorage.setItem("IdUserToShow", id);}}  
                        src={image} alt={image} />
                </Link>

                <Link to = "/">
                    <button className="btn btn-sm btn-outline-secondary" type="button" onClick={this.logout}>
                        Logout
                    </button>
                </Link>
            </nav>
        )
    }   
} 

export default withRouter(HeaderPrivate);

