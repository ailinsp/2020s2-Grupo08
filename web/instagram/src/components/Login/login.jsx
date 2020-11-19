import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import axios from "axios";
import "./Login.css";
import Notifications,{notify} from 'react-notify-toast';


const Login = () => {
  
  let myColor = { background: '#0E1717', text: "#FFFFFF" };
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
        history.push("/timelime");
      })
      .catch((error) => {
      console.log("error : ", error.response.data.message);
      const errorUser = error.response.data.message;
      notify.show(errorUser,"error",5000,myColor);

      });
  };

  return (

    <div className="container-fluid">
        <Notifications />
        <div className="row no-gutter">
            <div className="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
            <div className="col-md-8 col-lg-6">
              <div className="login d-flex align-items-center py-5">
                <div className="container">
                  <div className="row">
                    <div className="col-md-9 col-lg-8 mx-auto">

                      <h3 className="login-heading mb-4">Instagram</h3>
                                    
                      <form onSubmit={handleSubmit}>

                        <label htmlFor="email"> Email 
                          <input  type="text"
                                  name="email"
                                  value={data.email}
                                  onChange={handleInputChange}
                                  className="form-control"
                                  required autoFocus>
                          </input>
                        </label>

                        <label htmlFor="password"> Password
                          <input  type="password"
                                  name="password"
                                  value={data.password}
                                  onChange={handleInputChange}
                                  className="form-control"
                                  required autoFocus>
                          </input>
                        </label>

                        <button className="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" 
                                type="submit"> Login
                        </button>

                      </form>
                      
                      <a href="http://localhost:3000/register" className="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" 
                        role="button"> Register
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