import React, { Component } from 'react'
import '../Styles/Header.css'

import {FcMenu} from 'react-icons/fc'
 class Header extends Component {
  Responsive_menu=()=>{
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
            <h1>LOGO</h1>
        </div>
        <div className="Links" id="small_links">
       <div className='left'>
        <div className='home'>
          <a href ="/home">Home</a>
        </div>
        <div className='About'>
            <a href="/About" >ABOUT</a>
        </div>
        
        <div className="contact">
          < a href="/contact">CONTACT</a>
        </div>
        <div className="faq">
            <a href="/faq">FAQ</a>
        </div>
        </div>
        <div className="right">
           < a href="/login">LOGIN</a>
        </div>
       </div>
     
        </nav>
        < a href="menu" id="iconbar">
        <FcMenu/>
       </a>
      </div>
    )
  }
}

export default Header
