import React from "react";
import { BrowserRouter,Routes,Route} from "react-router-dom";
import Home from './pages/Home';
import Register from './pages/Register';
import Login from './pages/Login';
import jwt from 'jwt-decode'
import { setAuthToken } from "./common/setAuthToken";

export default function App() {
  let userDetails;

  function hasJWT() {
    let flag;
    let final = false;

    //check user has JWT token
    localStorage.getItem("Authorization") ? flag=true : flag=false
    
    //check jwt token exp
    if(flag){
      let token = localStorage.getItem("Authorization");
      token = token.replace('Bearer','');
      userDetails = jwt(token);
      if(userDetails.exp*1000 > Date.now()){
        final = true;
        setAuthToken(token);
      }
      else{
        logout();
      }
    }

    return final
  }
  const logout = () => {
    localStorage.removeItem('Authorization');
  };

  return (
    <BrowserRouter>
      <Routes>
          <Route path="/" element={ hasJWT() ? <Home username={userDetails.sub} /> : <Login/>}   />
          <Route path="/register" element={ hasJWT() ? <Home username={userDetails.sub} /> : <Register />} />
          <Route path="/login" element={ hasJWT() ? <Home username={userDetails.sub} /> : <Login />} />
      </Routes>
    </BrowserRouter>
  );
}

