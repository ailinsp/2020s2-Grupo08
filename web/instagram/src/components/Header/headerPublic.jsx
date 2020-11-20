import React from "react";
import "./Header.css";
  

class HeaderPublic extends React.Component {

    constructor(props) {
      super(props)
      this.state = {}
    } 

    render() {
                        //cambiar los a por link
        return(
            <nav class="navbar navbar-light justify-content-between" >
                <a className="Nav-brand-logo" href="/timeline">Instagram</a>
            </nav>
        )
    }   
} 

export default HeaderPublic;

