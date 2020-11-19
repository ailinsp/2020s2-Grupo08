import React from "react";
import "./Header.css";
import api from "../../Api/api"


class HeaderPrivate extends React.Component {

    constructor(props) {
      super(props)
      this.state = {
        searchValue : "",
        image: "",
        id: "" 
      }
      
    } 

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

    busqueda (searchValue){
        this.props.history.push(`http://localhost:3000/search?q='${searchValue}`)
    }


    render() {
        const {searchValue, id, image} = this.state;

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

                    <button type="submit" 
                            className="btn btn-sm btn-outline-secondary" 
                            disabled = {this.state.searchValue === ""} 
                            onClick= {(searchValue) => this.busqueda(searchValue)}>
                            Search
                    </button>
                </form>
                    
                <img class="rounded-circle Image-style" onClick={() => {
                    localStorage.setItem("IdUserToShow", id);
                    window.location.href='http://localhost:3000/profile'}}  
                    src={image} alt={image} />
            </nav>
        )
    }   
} 

export default HeaderPrivate;

