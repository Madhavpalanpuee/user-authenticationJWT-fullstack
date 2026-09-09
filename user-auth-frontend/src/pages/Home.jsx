import { useEffect, useState } from "react"; // React ke useEffect aur useState hooks import kar rahe hain.
// import axios from "axios"; // Backend API call karne ke liye Axios import kar rahe hain.

import api from "../services/authService";// Hamara custom Axios instance import kar rahe hain


import Navbar from "../components/Navbar";// Navbar component import kar rahe hain

function Home() { // Home component create kar rahe hain.

  const [message, setMessage] = useState(""); // Backend se aane wale message ko store karne ke liye state.

  useEffect(() => { // Component browser par load hote hi ye code execute hoga.

    // axios.get("http://localhost:8080/api/auth/home") // Backend ke /home endpoint ko GET request bhej rahe hain.// pehle hum ye use kar rahe the fir hum nr custom api bana li 
       
    api.get("/home") // Custom Axios instance se protected API call kar rahe hain

      .then((response) => { // Agar backend ne successfully response diya to ye chalega.

        setMessage(response.data); // Backend se aaye response ko message state me store kar rahe hain.
      })

      .catch((error) => { // Agar API call fail hui to ye chalega.

        console.error("Home API Error:", error); // Error ko browser console me print kar rahe hain.
      });

  }, []); // Empty dependency array ka matlab: component load hone par sirf ek baar API call.


  return (
    <div> {/* Home page ka main container */}

    
    <Navbar />  {/* Navbar show kar rahe hain */}

       {/* Home page content */}
      <div className="home-container">

      <h1>{message}</h1> {/* Backend se aaya welcome message screen par show hoga */}

      <p>You are successfully logged in.</p> {/* Login successful hone ka message */}

       </div>
        
    </div>
  );
}



export default Home; // Home component ko export kar rahe hain.




// Home
//  ↓
// Navbar
//  ↓
// localStorage se name
//  ↓
// Welcome, Madhav


// Saath me Home API:

// Home.jsx
//    ↓
// api.get("/home")
//    ↓
// Axios Request Interceptor
//    ↓
// JWT attach
//    ↓
// Spring Boot
//    ↓
// JwtAuthenticationFilter
//    ↓
// JWT validate
//    ↓
// SecurityContext
//    ↓
// Database
//    ↓
// Welcome Madhav!