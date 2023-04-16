import React, { Component } from 'react'
import '../Styles/Mainpage.css'
import GetMap from './Map'
import Donut from './Donut'
import { Link } from 'react-router-dom'
import Bar from './Bar'
import Line from './Line'
class Mainpage extends Component {

  render() {
    return (
      <>
        < div className="Main">
          <GetMap />
          <div className="upper"><Bar /></div>
          <div className="Lower">
            <div className="Left_Lower_half"><Line /></div>
            <div className="Right_Lower_half"><Donut /></div>


          </div>
        </div>
        <h2>Please click on the below link to access the admin page</h2>
        <div>
          <Link className='mylink' style={{ textDecoration: 'none' }} to="/admin"><b>Admin</b></Link>
        </div>
      </>
    )
  }
}

export default Mainpage
