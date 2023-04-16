import React from 'react'
import '../Styles/Card.css'
const Card = (props) => {
  return (
    <>
    <div className='card_comp'>
    <div className="card">
      <div className="img_class">
        <img src={props.Img} alt="my img"/>
        </div>
        <div className="person_name">
        
        {props.name}
        </div>
        <div className="Link">
        <div className='link'>
        <a href={props.Url} >
            <img className="Link_img" src="icons8-instagram-94.png"/>
        </a>
        </div>
        <div className='link'>
        <a href={props.Url1}>
            <img className="Link_img linkdin" src="linkedin.png" alt="Linkdin"/>
        </a>
        </div>
        </div>
    </div>
    </div>
    </>
  )
}

export default Card;
