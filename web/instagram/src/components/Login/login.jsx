import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import axios from "axios";
import "./Login.css";


const Login = () => {
  const history = useHistory();
  const [data, setData] = useState({
    email: "",
    password: "",
  });

  const handleInputChange = (event) => {
    setData({
      ...data,
      [event.target.name]: event.target.value,
    });
  };

  const handleSubmit = (event) => { 
    event.preventDefault();
    axios
      .post("http://localhost:7000/login", data)   
      .then((response) => {
        localStorage.setItem("token", response.headers.authorization);
        localStorage.setItem("userData", JSON.stringify(response.data));
        history.push("/profile");
      })
      .catch((error) => console.log("Error: LOGIN FAILED", error));
  };

  return (
    <div className="container-fluid">
        <div className="row no-gutter">
            <div className="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
                <div className="col-md-8 col-lg-6">
                    <div className="login d-flex align-items-center py-5">
                         <div className="container">
                             <div className="row">
                                <div className="col-md-9 col-lg-8 mx-auto">
                                    <h3 className="login-heading mb-4">Instagram</h3>
                                    
                                    <form onSubmit={handleSubmit}>
                                      <label htmlFor="email">
                                        Email 
                                        <input
                                          type="text"
                                          name="email"
                                          value={data.email}
                                          onChange={handleInputChange}
                                          className="form-control"
                                        ></input>
                                      </label>

                                      <label htmlFor="password">
                                        Password
                                        <input
                                          type="password"
                                          name="password"
                                          value={data.password}
                                          onChange={handleInputChange}
                                          className="form-control"
                                        ></input>
                                      </label>

                                      <button className="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">
                                        Login
                                      </button>
                                    </form>
                                      <a href="http://localhost:3000/register" className="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" role="button">
                                       Register
                                      </a>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
    );

};

export default Login;