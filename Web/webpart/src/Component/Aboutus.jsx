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
              We are the members of the team Cyber Savvy Squad. And our idea is related to solve the problem of
              waste management.Key feature of our website and app is to locate
              the garbage on a map and provide information to
              the people.Whenever a person throws garbage , he/she notify us it’s location amount and garbage type
              through our App.
              On WebSite and in App we will show how much and where garbages are present in the world on a
              map with the help of heatmap feature.
            </div>
          </div>
          <div className="Group_image">
            <img src={group_image} />
          </div>
        </div>
        <div className="Memeber">
          <div className="Member">
            Member
          </div>

          <div className='Member_details'>
            <Person />
          </div>
        </div>

      </div>
    )
  }
}

export default Aboutus
