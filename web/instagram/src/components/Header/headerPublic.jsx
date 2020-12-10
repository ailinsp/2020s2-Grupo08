import React from "react";
import "./Header.css";
import { Link } from 'react-router-dom';
  

class HeaderPublic extends React.Component {

    constructor(props) {
      super(props)
      this.state = {}
    } 

    render() {
        return(
            <nav class="navbar navbar-light justify-content-between" >
                <Link className="Nav-brand-logo" to="/timeline">Instagram</Link>
            </nav>
        )
    }   
} 

export default HeaderPublic;

