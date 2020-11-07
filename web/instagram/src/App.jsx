import React, { Component } from 'react';
import { BrowserRouter as Router, Switch } from "react-router-dom";
import './App.css';
import Login from './components/Login/login';
import Register from './components/Register/register';
import Header from './components/Header/header';
import Home from './components/Home/home';

import PublicRoute from "./PublicRoute";
//import PrivateRoute from "./PrivateRoute";



class App extends Component {
  render() {
    return (
      <Router>
      <Header />
      <div className="container">
        <Switch>
        <PublicRoute path="/login" component={Login} />
        <PublicRoute path="/register" component={Register} />
        <PublicRoute path="/home" component={Home} />
        </Switch>
      </div>
    </Router>
    );
  }
}
export default App;
