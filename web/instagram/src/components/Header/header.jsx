import React from "react";
import "./Header.css";
import api from "../../Api/api"

const isAuthenticated = !!localStorage.getItem("token");
  
class Header extends React.Component {

    constructor(props) {
      super(props)
      this.state = {
        searchValue : "",
        image: "",
        id: "" 
      }
      
    }  

    onChange = e =>  {
        this.setState({searchValue: e.target.searchValue});

    }
    
    getUserData= () => {
        return api.getUserLogged()
                    .then(usuario => {
                        this.setState({
                            name: usuario.name,
                            image : usuario.image,
                            id: usuario.id,
                        })
                    }
                    ).catch(error => this.setState({ error }))
    }
    
    componentDidMount(){
        this.getUserData()
    }

    busqueda (searchValue){
        this.props.history.push(`http://localhost:3000/search?q='${searchValue}`)
    }

    render() {
        const {searchValue, id, image} = this.state;
        return (
            <header>
                <nav className = "navbar navbar-default navbar-fixed-top">
                    <div className = "container-fluid">
                        <div className ="navbar-header">

                            <a className="Nav-brand-logo" href="/timeline">
                                    Instagram
                            </a>
                            
                            {isAuthenticated && (
                                <div class="row"> 
                                <div class="col-sm"> 
                                    <div className = "form-group">
                                        <input 
                                            type = "text" 
                                            className ="form-control" 
                                            placeholder="Search" 
                                            value={this.state.searchValue}
                                            onChange = {this.onChange}>
                                        </input>
                                    </div>
                                </div>
                                <div class="col-sm"> 
                                    <div className="buttonContainer text-right">
                                        <button type="text" 
                                                className="btn btn-primary" 
                                                disabled = {this.state.searchValue === ""} 
                                                onClick= {
                                                    (searchValue) => this.busqueda(searchValue)     
                                                }
                                                >
                                                Search
                                        </button>
                                    </div>
                                </div>
                                <div class="col-sm">  
                                    <div>
                                        <img onClick={() => {
                                            localStorage.setItem("IdUserToShow", id);
                                            window.location.href='http://localhost:3000/profile'
                                        } }  src={image} alt={image} />
                                    </div>  

                                </div> 
                                </div> 
                            )}
                        </div>
                    </div>
                </nav>
            </header>         
        );
    }   
} 

export default Header;