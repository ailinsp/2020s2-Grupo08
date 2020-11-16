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
            <nav className="Nav">
                <div className="Nav-menus">
                    <div className="Nav-brand">
                        <a className="Nav-brand-logo" href="/timeline">
                            Instagram
                        </a>
                    </div>
                </div>
                {isAuthenticated && (
                    <button type="button" onClick={logout}>
                        Logout
                    </button>
                )}
            </nav>
    );
}   

export default Header;