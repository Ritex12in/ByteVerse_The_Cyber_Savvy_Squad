import React, { Component } from 'react'
import '../Styles/Loginform.css'
import {FcLock,FcBusinessContact} from "react-icons/fc";

 class Loginform extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         email:" ",
         password:" "
      }
    }
    HandleEmail=(event)=>{
        this.setState({
         email:event.target.value
        })
     }
   HandlePass=(event)=>{
        this.setState({
            password:event.target.value
         
        })
     }
     HandleSubmit=(event)=>{
        alert(`your email is ${this.state.email} and passord is ${this.state.password}`)
        event.prevent.default()
     }

  render() {
    return (
      <>
      <form onSubmit={this.HandleSubmit}>
      <div className="form">
      <h1 >LOGIN </h1>
     
      <div className="form-input">
      <div className="form-label">
      <label htmlfor="name">Username: </label></div>
      <FcBusinessContact className='user'/>
        <input type="text" id="email" name="username" onChange={this.HandleEmail} required/>
        </div>
        <div className="form-input">
        <div className="form-label">
        <label htmlfor="password">Password:</label>
        </div>
        <FcLock/>
        <input type="password" minLength="4" maxlenght="10"id="password"  name="password" onChange={this.HandlePass} required/>
        </div>
        <button type="submit" className="button">Login</button>
        <div class="forget-link">
          <a href="#">forget password</a>
        
        </div>
       
 <div className="google">
 <div className="Login_with">
  LOGIN WITH <img  className="google_icon" src="icons8-google-48.png"/>
  </div>
 </div>
      </div>
      
        </form>
        </>
    )
  }
}

export default Loginform
