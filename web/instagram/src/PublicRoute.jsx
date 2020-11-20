import React from "react";
import { Redirect, Route } from "react-router-dom";
import HeaderPublic from './components/Header/headerPublic';


const PublicRoute = ({ path, component }) => {
  const isAuthenticated = !!localStorage.getItem("token");

  if (isAuthenticated) return <Redirect to={"/timeline"} />;

  return (
    <div className="container">
         <HeaderPublic />
         <Route path={path} component={component} />;
    </div>)
};

export default PublicRoute;