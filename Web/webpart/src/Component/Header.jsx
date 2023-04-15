import React, { Component } from 'react'
import '../Styles/Header.css'
import {Link} from 'react-router-dom'
import {FcMenu} from 'react-icons/fc'
 class Header extends Component {
  constructor(props) {
    super(props)
  
    this.state = {
       val:false
    }
  }
  
  Responsive_menu=()=>{
    this.setState({
     val:true
    })
 
  
     
    var e=document.getElementById('small_links');
    if(e.className==='Links')
    {
      e.className+=" responsive";
    }
    else{
      e.className='Links';
    }
  }
  render() {
  
    return (
      <div className="box">
      <nav>
     
        <div className="logo">
           <img src="splash_screen_image.jpg" alt="LOGO"/>
        </div>
        <div className="Links" id="small_links">
       <div className='left'>
        <div className='home'>
          <Link className="mylink" style={{textDecoration: 'none'}} to="/">Home</Link>
        </div>
        <div className='About'>
            <Link className="mylink" style={{textDecoration: 'none'}} to="/About" >ABOUT</Link>
        </div>
        
        <div className="contact">
          <Link className="mylink" style={{textDecoration: 'none'}} to="/contact">CONTACT</Link>
        </div>
        <div className="faq">
            <Link className="mylink" style={{textDecoration: 'none'}} to="/faq">FAQ</Link>
        </div>
        </div>
        <div className="right">
           <Link className="mylink login"  style={{textDecoration: 'none'}} to='/login'>LOGIN</Link>
        </div>
       </div>
     
        </nav>
        <Link to="#" id="iconbar" onClick={this.Responsive_menu}>
      <img src="icons8-hamburger-menu-64.png"/>
       </Link>
      </div>
    )
  }
}

export default Header
