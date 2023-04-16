import React, { Component } from 'react'
import "../Styles/aboutus.css"
import group_image from './group_image.jpg';
import Person from './Person'
 class Aboutus extends Component {
  render() {
    return (
      <div className="About_page">
        <h1> ABOUT-US</h1>
        <div className="About_us_section">
        <div className="wriiten_About_us">
        <div className='dummy_about'>
        Lorem Ipsum is simply dummy text of the printing and typesetting industry.
        Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
        when an unknown printer took a galley of type and scrambled it to make a type
        specimen book. It has survived not only five centuries, but also the leap into 
        electronic typesetting, remaining essentially unchanged. It was popularised in 
        the 1960s with the release of Letraset sheets containing Lorem Ipsum passages,
        and more recently with desktop publishing software like Aldus PageMaker 
        including versions of Lorem Ipsum.
        </div>
        </div>
        <div className="Group_image">
            <img src={group_image}/>
        </div>
        </div>
        <div className="Memeber">
        <div className="Member">
         Member
        </div>
        
        <div className='Member_details'>
        <Person/>
        </div>
        </div>
     
      </div>
    )
  }
}

export default Aboutus
