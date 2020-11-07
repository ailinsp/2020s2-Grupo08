import React, { Component } from 'react';
import { BrowserRouter as Router, Switch } from "react-router-dom";
import './App.css';
import Login from './components/Login/login';
import Header from './components/Header/header';



class App extends Component {
  render() {
    return (
      <Router>
      <Header />
      <div className="container">
        <Switch>
          <Login />

        </Switch>
      </div>
    </Router>
    );
  }
}
export default App;
