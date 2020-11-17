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
            <nav class = "navbar navbar-default navbar-fixed-top">
                <div class = "container-fluid">
                    <div class ="navbar-header">
                        <a className="Nav-brand-logo" href="/timeline">
                                Instagram
                        </a>
                        
                        {isAuthenticated && (
                            <form action = "" class="navbar-form navbar-left" role ="Search">
                                <div class = "form-group">
                                    <input 
                                        type = "text" class ="form-control" placeholder="Buscar">
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