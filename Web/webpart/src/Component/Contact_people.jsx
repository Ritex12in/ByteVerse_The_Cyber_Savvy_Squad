import React from 'react'
import '../Styles/Contact_people.css'
function Contact() {
  return (
    <div className="contact_people">
    <div className="main1">
      <h1>Contact Us</h1>

      <form className='gh'>
        <div className='input1'>
          <label className='label_text'>Name</label>
          <input type="text" required placeholder="Abc" />
        </div>

        <div className='input1'>
          <label className='label_text'>Email</label>
          <input type="email" required placeholder="Abc@xyz.com" />
        </div>
        <div className='input1'>
          <label className='label_text'>Message</label>
          <input
            type="text"
            required
            placeholder="Tell us about your query..."
          />
        </div>

        <button  className=" button_submit" type="submit">Send</button>
      </form>
      </div>
  </div>
  )
}

export default Contact

