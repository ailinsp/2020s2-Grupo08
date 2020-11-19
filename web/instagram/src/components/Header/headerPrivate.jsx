import React from "react";
import "./Header.css";
import api from "../../Api/api"
import PropTypes from "prop-types";
import { withRouter } from 'react-router'



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
        this.setState({searchValue: e.target.searchValue});

    }

    componentDidMount(){
        this.getUserData()
    }


    logout(){
        localStorage.removeItem("token");
        localStorage.removeItem("IdUserLogged")
        localStorage.removeItem("IdUserToShow")
        localStorage.removeItem("IdPostToShow")
        window.location.href='http://localhost:3000/'
    }


    render() {
        const {searchValue, id, image} = this.state;
        const { match, location, history } = this.props;

        return(
            <nav class="navbar navbar-light justify-content-between" >

                <a className="Nav-brand-logo" href="/timeline">Instagram </a>

                <form class="form-inline">
                    <input  type = "text" 
                            className ="form-control mr-sm-2" 
                            placeholder="Search" 
                            value={this.state.searchValue}
                            onChange = {this.onChange}>
                    </input>

                    <button type="text" 
                            className="btn btn-sm btn-outline-secondary" 
                            disabled = {this.state.searchValue === ""} 
                            onClick= {() => history.push(`http://localhost:3000/search?q='${searchValue}`)}>
                            Search
                    </button>
                </form>
                    
                <img class="rounded-circle Image-style" onClick={() => {
                    localStorage.setItem("IdUserToShow", id);
                    window.location.href='http://localhost:3000/profile'}}  
                    src={image} alt={image} />
                <button className="btn btn-sm btn-outline-secondary" type="button" onClick={this.logout}>
                    Logout
                </button>
            </nav>
        )
    }   
} 

export default withRouter(HeaderPrivate);
