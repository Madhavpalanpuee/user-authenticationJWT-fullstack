import { useState } from "react"; // React se useState Hook import kar rahe hain.
import { registerUser } from "../services/authService"; // authService file se registration API function import kar rahe hain.
import { Link } from "react-router-dom";// React Router se Link import kar rahe hain

function Register() { // Register naam ka React functional component bana rahe hain.

  const [name, setName] = useState(""); // Name ki current value aur usko update karne ke liye state bana rahe hain.

  const [email, setEmail] = useState(""); // Email ki current value aur usko update karne ke liye state bana rahe hain.

  const [password, setPassword] = useState(""); // Password ki current value aur usko update karne ke liye state bana rahe hain.

    const [error, setError] = useState(""); // Validation ya API error ko store karenge

const handleSubmit = async (event) => { // Form submit hone par ye asynchronous function chalega.
    event.preventDefault(); // Form submit hone par page ko automatically refresh hone se rok rahe hain.

     setError(""); // Purana error clear kar rahe hain

    if (!name.trim()) { // Name empty hai ya nahi check kar rahe hain

      setError("Name is required"); // Error message set kar rahe hain

      return; // Function ko yahi stop kar rahe hain
    }

    if (!email.trim()) { // Email empty hai ya nahi check kar rahe hain

      setError("Email is required"); // Email required ka error set kar rahe hain

      return; // Aage ka code execute nahi hoga
    }

    if (!email.includes("@")) { // Simple email format check kar rahe hain

      setError("Please enter a valid email"); // Invalid email ka error set kar rahe hain

      return; // Function stop kar rahe hain
    }

    if (!password) { // Password empty hai ya nahi check kar rahe hain

      setError("Password is required"); // Password required ka error set kar rahe hain

      return; // Function stop kar rahe hain
    }

    if (password.length < 6) { // Password ki minimum length check kar rahe hain

      setError("Password must be at least 6 characters"); // Short password ka error set kar rahe hain

      return; // Function stop kar rahe hain
    }

    try { // API request ko safely handle karne ke liye try block start kar rahe hain.

     const userData = { // User ka registration data ek object ke andar store kar rahe hain. 
     name: name, // Name state ki value ko userData ke name property me store kar rahe hain.
     email: email, // Email state ki value ko userData ke email property me store kar rahe hain.
     password: password // Password state ki value ko userData ke password property me store kar rahe hain.
     }; // userData object yahan close ho raha hai. 
     
     const response = await registerUser(userData); // React se Spring Boot backend ko registration request bhej rahe hain aur response ka wait kar rahe hain.

     console.log("Registration Successful:", response.data); // Backend se aaya response console me print kar rahe hain.

     setName(""); // Registration successful hone ke baad name input ko empty kar rahe hain.
     setEmail(""); // Registration successful hone ke baad email input ko empty kar rahe hain.
     setPassword(""); // Registration successful hone ke baad password input ko empty kar rahe hain.

     alert("Registration Successful!"); // User ko registration successful hone ka message dikha rahe hain.

     } catch (error) { // Agar API request me error aata hai to catch block execute hoga. 

     console.error("Registration Failed:", error); // Error ki details browser console me print kar rahe hain.
     
    setError("Registration failed. Please try again."); // User ko error message dikha rahe hain
     } // try-catch block yahan close ho raha hai.

  }; // handleSubmit function yahan close ho raha hai.

  return ( // Component ka JSX/UI return karna start kar rahe hain.

    <div className="auth-container"> {/* Saare registration elements ko ek parent container ke andar rakh rahe hain. */}
      
      <div className="auth-card">  {/* Registration card */}

      <h1>User Registration</h1> {/* Page par User Registration heading display kar rahe hain. */}

      {error && ( // Agar error empty nahi hai to error message show hoga
          <p>
            {error} {/* Error state me stored message display kar rahe hain */}
          </p>
        )}

      <form onSubmit={handleSubmit}> {/* Form submit hone par handleSubmit function call hoga. */}

        <input // User ka name enter karne ke liye input field bana rahe hain.
          type="text" // Input ka type normal text rakha hai.
          placeholder="Enter your name" // Input ke andar hint text dikhayega.
          value={name} // Input ki value ko name state ke saath connect kar rahe hain.
          onChange={(event) => setName(event.target.value)} // User jo type karega us value se name state update hogi.
        />

        <br /> {/* Next content ko new line mein lane ke liye line break de rahe hain. */}
        <br /> {/* Thoda extra vertical space dene ke liye doosra line break de rahe hain. */}

        <input // User ka email enter karne ke liye input field bana rahe hain.
          type="email" // Input ko email type bana rahe hain jisse basic email validation milti hai.
          placeholder="Enter your email" // Email input ke andar hint text dikhayega.
          value={email} // Input ki value ko email state ke saath connect kar rahe hain.
          onChange={(event) => setEmail(event.target.value)} // User jo email type karega us value se email state update hogi.
        />

        <br /> {/* Next input ko new line mein lane ke liye line break de rahe hain. */}
        <br /> {/* Thoda extra vertical space dene ke liye doosra line break de rahe hain. */}

        <input // User ka password enter karne ke liye input field bana rahe hain.
          type="password" // Password type use kar rahe hain taaki typed characters hide ho jayein.
          placeholder="Enter your password" // Password input ke andar hint text dikhayega.
          value={password} // Input ki value ko password state ke saath connect kar rahe hain.
          onChange={(event) => setPassword(event.target.value)} // User jo password type karega us value se password state update hogi.
        />

        <br /> {/* Button ko new line mein lane ke liye line break de rahe hain. */}
        <br /> {/* Button se pehle extra vertical space dene ke liye line break de rahe hain. */}

        <button type="submit"> {/* Submit button bana rahe hain; click karne par form submit hoga. */}
          Register {/* Button par Register text display kar rahe hain. */}
        </button> {/* Register button yahan close ho raha hai. */}

      </form> {/* Registration form yahan close ho raha hai. */}

      <p  className="auth-link"> {/* Login page par jaane ka message */}
  Already have an account?{" "} {/* User ko bataya ja raha hai ki account already hai */}
  
  <Link to="/login"> {/* Login page ke /login route par le jayega */}
    Login
  </Link>
</p>

  </div>

    </div> // Parent container yahan close ho raha hai.

  

  ); // JSX return statement yahan close ho raha hai.
} // Register component yahan close ho raha hai.

export default Register; // Register component ko doosri files mein import karne ke liye export kar rahe hain.




// 🔥 Is component ka complete flow
// User Name/Email/Password type karta hai
//             ↓
//        useState me save
//             ↓
//       Register button
//             ↓
//       handleSubmit()
//             ↓
//     event.preventDefault()
//             ↓
//        userData object
//             ↓
//    registerUser(userData)
//             ↓
//        Axios POST request
//             ↓
// Spring Boot /api/auth/register
//             ↓
//       User database me save
//             ↓
//         response.data
//             ↓
//    "Registration Successful!"