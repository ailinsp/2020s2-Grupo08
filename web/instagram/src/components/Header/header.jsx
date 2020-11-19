import React from "react";
import "./Header.css";



const isAuthenticated = !!localStorage.getItem("token");
  
class Header extends React.Component {

    constructor(props) {
      super(props)
      this.state = {
        searchValue : "",
      }
    }  

    onChange = e =>  {
        this.setState({searchValue: e.target.searchValue});

    }

    render() {
        const {searchValue} = this.state;
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
                                            type = "text" 
                                            className ="form-control" 
                                            placeholder="Search" 
                                            value={this.state.searchValue}
                                            onChange = {this.onChange}>
                                        </input>
                                    </div>
    
                                    <div className="buttonContainer text-right">
                                        <button type="text" 
                                                className="btn btn-primary" 
                                                disabled = {this.state.searchValue === ""} 
                                                onClick={() => {
                                                    localStorage.setItem("searchValue", searchValue); 
                                                    window.location.href='http://localhost:3000/search'
                                                }}
                                                >
                                                Search
                                        </button>
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