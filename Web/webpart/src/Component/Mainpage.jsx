import React, { Component } from 'react'
import '../Styles/Mainpage.css'
import GetMap from './Map'
import Donut from './Donut'
class Mainpage extends Component {

  render() {
    return (
      <>
        < div className="Main">
          <GetMap />
          <div className="upper"></div>
          <div className="Lower">
            <div className="Left_Lower_half"></div>
            <div className="Right_Lower_half"><Donut /></div>


          </div>
        </div>
      </>
    )
  }
}

export default Mainpage
