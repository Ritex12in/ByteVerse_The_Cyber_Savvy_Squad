import React, { useEffect, useState } from 'react'
import '../Styles/Loginform.css'
import { FcLock, FcBusinessContact } from "react-icons/fc";
import { useFirebase } from '../context/firebase';
import { useNavigate } from 'react-router-dom';

const Loginform = () => {

  const firebase = useFirebase();
  const navigate = useNavigate();
  useEffect(() => {
    if (firebase.isLoggedin) {
      navigate("/");
    }
    else navigate("/login");
  }, [firebase, navigate]);

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const HandleSubmit = async (event) => {
    event.preventDefault()
    const detail = await firebase.signin(email,password);
    console.log("success", detail);
    alert(`your email is ${email} and passord is ${password}`)
  }
  return (
    <>
      <form onSubmit={HandleSubmit}>
        <div className="form">
          <h1 >LOGIN </h1>
          <h3>Hey {email}</h3>
          <div className="form-input">
            <div className="form-label">
              <label htmlfor="name">Username: </label></div>
            <FcBusinessContact />
            <input type="text" id="email" name="username" onChange={e => setEmail(e.target.value)} value={email} required />
          </div>
          <div className="form-input">
            <div className="form-label">
              <label htmlfor="password">Password:</label>
            </div>
            <FcLock />
            <input type="password" minLength="4" maxlenght="10" id="password" name="password" onChange={e => setPassword(e.target.value)} value={password} required />
          </div>
          <button type="submit" className="button">Login</button>
          <div class="forget-link">
            <a href="#">forget password</a>

          </div>
           </div>
          </form>
          <div className="google">
            <button className="Login_with" onClick={firebase.signinWithGoogle}>
              LOGIN WITH <img className="google_icon" src="icons8-google-48.png" />
            </button>
          </div>
    </>
  )

}

export default Loginform;
