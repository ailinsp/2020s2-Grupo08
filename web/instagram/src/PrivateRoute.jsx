import React from "react";
import { Redirect, Route } from "react-router-dom";
import axios from "axios";
import Header from './components/Header/header';

const PrivateRoute = ({ path, component }) => {
  const isAuthenticated = !!localStorage.getItem("token");

  if (!isAuthenticated) return <Redirect to={"/"} />;
  axios.defaults.headers['authorization'] = localStorage.getItem("token");

  return( 
    <div className="container">
         <Header />
         <Route path={path} component={component} />
  </div>)
};

export default PrivateRoute;