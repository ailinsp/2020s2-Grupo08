import React from "react";

const Register = () => {
  return (<div class="container-fluid">
  <div class="row no-gutter">
      <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
          <div class="col-md-8 col-lg-6">
              <div class="login d-flex align-items-center py-5">
                   <div class="container">
                       <div class="row">
                          <div class="col-md-9 col-lg-8 mx-auto">
                              <h3 class="login-heading mb-4">Complete the fields</h3>
                              <form>
                              <div class="form-label-group">
                                      <input type="" id="inputName" class="form-control" placeholder="Name" required autofocus></input>
                                      <label for="inputName">Name</label>
                                  </div>
                                  <div class="form-label-group">
                                      <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus></input>
                                      <label for="inputEmail">Email</label>
                                  </div>
                                  <div class="form-label-group">
                                      <input type="password" id="inputPassword" class="form-control" placeholder="Password" required></input>
                                      <label for="inputPassword">Password</label>
                                  </div>
                                  <div class="form-label-group">
                                      <input type="" id="inputImage" class="form-control" placeholder="Image" required autofocus></input>
                                      <label for="inputImage">Image</label>
                                  </div>
                                  <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">Register</button>
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

export default Register;