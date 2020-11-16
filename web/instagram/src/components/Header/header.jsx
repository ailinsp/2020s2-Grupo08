import React from "react";
import "./Header.css";
import { useHistory } from "react-router-dom";


const Header = () => {
    const history = useHistory();
    
    const logout = () => {
      localStorage.removeItem("token");
      history.push("/");
    };

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

                        <form action = "" class="navbar-form navbar-left" role ="Search">
                             <div class = "form-group">
                        <input type = "text" class ="form-control" placeholder="Buscar">

                        </input>
                    </div>
                </form>

                {isAuthenticated && (
                    <button type="button" onClick={logout}>
                        Logout
                    </button>
                )}
                      
                    </div>
                </div>
            </nav>
        </header>         

    );
}   

export default Header;