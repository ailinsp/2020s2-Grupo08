  
import React, { Component } from 'react';
import { BrowserRouter as Router, Switch } from "react-router-dom";
import './App.css';
import PublicRoute from "./PublicRoute";
import PrivateRoute from "./PrivateRoute";
import Login from './components/Login/login';
import Register from './components/Register/register';
import Timeline from './components/Timeline/timeline';
import ProfileUser from './components/Profile/profileUser';
import Post from './components/Post/post';
import Search from './components/Search/search';


class App extends Component {
  render() {
    return (
      <Router>
     
      <div className="container">
        <Switch>
        <PublicRoute path="/register" component={Register} />
        <PrivateRoute path="/profile"component={ProfileUser} />
        <PrivateRoute path="/timeline" component={Timeline} />
        <PrivateRoute path="/post" component={Post} />
        <PrivateRoute path="/search" component={Search} />
        <PublicRoute path="/"component={Login} />
        </Switch>
      </div>
    </Router>
    );
  }
}
export default App;
