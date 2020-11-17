import React from "react";
import "./Header.css";
import { useHistory } from "react-router-dom";


const Header = () => {
    const history = useHistory();
    
  

    const profile = () => {
        history.push("/profile");
      };
  
    const isAuthenticated = !!localStorage.getItem("token");
  
    return (
        <header>
            <nav className = "navbar navbar-default navbar-fixed-top">
                <div className = "container-fluid">
                    <div className ="navbar-header">
                        <a className="Nav-brand-logo" href="/timeline">
                                Instagram
                        </a>
                        
                        {isAuthenticated && (
                            <form action = "" className="navbar-form navbar-left" role ="Search">
                                <div className = "form-group">
                                    <input 
                                        type = "text" className ="form-control" placeholder="Buscar">
                                    </input>
                                </div>
                            </form>
                        )}

                    </div>
                </div>
            </nav>
        </header>         

    );
}   

export default Header;