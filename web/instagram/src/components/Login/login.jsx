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
    <div class="container-fluid">
        <div class="row no-gutter">
            <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
                <div class="col-md-8 col-lg-6">
                    <div class="login d-flex align-items-center py-5">
                         <div class="container">
                             <div class="row">
                                <div class="col-md-9 col-lg-8 mx-auto">
                                    <h3 class="login-heading mb-4">Instagram</h3>
                                    
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

                                      <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">Login</button>
                                      <a href="http://localhost:3000/register" class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" role="button">Register</a>
                                     </form>
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