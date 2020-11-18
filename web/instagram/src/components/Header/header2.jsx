import React from "react";
import "./Header.css";


const isAuthenticated = !!localStorage.getItem("token");
  
class Header extends React.Component {

    constructor(props) {
      super(props)
      this.state = {
        searchValue : ""
      }
    }  

    openSearchView= (searchValue) => {   
        this.props.history.push('/search', { searchValue: searchValue})
    }


    render() {
        return (
            <header>
                <nav className = "navbar navbar-default navbar-fixed-top">
                    <div className = "container-fluid">
                        <div className ="navbar-header">

                            <a className="Nav-brand-logo" href="/timeline">
                                    Instagram
                            </a>
                            
                            {isAuthenticated && (
                                <div> 
                                    <div className = "form-group">
                                        <input 
                                            type = "text" className ="form-control" placeholder="Search" 
                                            value={""}>
                                        </input>
                                    </div>
    
                                    <div className="buttonContainer text-right">
                                        <button type="text" className="btn btn-primary" disabled = {this.state.value === ""} onClick={()=>this.openSearchView()}>Search</button>
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