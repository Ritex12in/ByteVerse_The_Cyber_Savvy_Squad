import React from 'react'
import Mainpage from './Component/Mainpage.jsx'
import Header from './Component/Header.jsx'

import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Loginform from './Component/Loginform.jsx'
import Aboutus from './Component/Aboutus.jsx'
<<<<<<< HEAD
import Contact from './Component/Contact_people.jsx'

=======
import Admin from './pages/Admin.jsx'
>>>>>>> c92092fcd17aa32c7d0b8d86129e7401589f3536
function App() {
  console.log(process.env)
  return (
    <div className='App'>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path='/' element={<Mainpage />} />
           
        <Route path='/contact' element={<Contact/>}/>
          <Route path='/login' element={<Loginform />} />
      
          <Route path='/About' element={<Aboutus />} />
<<<<<<< HEAD
       
=======
          <Route path='/admin' element={<Admin/>}/>
>>>>>>> c92092fcd17aa32c7d0b8d86129e7401589f3536
        </Routes>

      </BrowserRouter>


    </div>
  );
}

export default App;
