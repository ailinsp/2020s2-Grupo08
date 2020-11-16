  
import React, { Component } from 'react';
import { BrowserRouter as Router, Switch } from "react-router-dom";
import './App.css';
import Login from './components/Login/login';
import Register from './components/Register/register';
import Header from './components/Header/header';
import Timeline from './components/Timeline/timeline';
import ProfileUser from './components/Profile/profileUser';

import PublicRoute from "./PublicRoute";
import PrivateRoute from "./PrivateRoute";



class App extends Component {
  render() {
    return (
      <Router>
      <Header />
      <div className="container">
        <Switch>
        <PublicRoute path="/register" component={Register} />
        <PrivateRoute path="/profile"component={ProfileUser} />
        <PrivateRoute path="/timeline" component={Timeline} />
        <PublicRoute path="/"component={Login} />
        </Switch>
      </div>
    </Router>
    );
  }
}
export default App;
