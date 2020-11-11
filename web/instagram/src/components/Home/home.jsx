import { Component } from "react";
import React from 'react'


class Home extends Component {
    constructor(props) {
        super(props);
       
        this.state = {
            
            email : this.props.location.state.email
    
            
        };

    }


    render() {
        return (
            <div className="m-5">
                <div>Profile</div> 
               
            </div>
        );
    }
}

export default Home;