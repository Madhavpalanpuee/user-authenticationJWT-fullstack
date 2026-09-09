import { useState } from "react"; // React ka useState hook import kar rahe hain.
import { loginUser } from "../services/authService"; // authService se login API function import kar rahe hain.
import { useNavigate, Link } from "react-router-dom"; // React Router se useNavigate hook import kar rahe hain.// iska use Page change karne ke liye useNavigate import kar rahe hain


function Login() { // Login naam ka React functional component bana rahe hain.

const [email, setEmail] = useState(""); // Email ko state me store karne ke liye state bana rahe hain.
const [password, setPassword] = useState(""); // Password ko state me store karne ke liye state bana rahe hain.
 const [error, setError] = useState(""); // Error message ko state me store karenge
const navigate = useNavigate(); // navigate function receive kar rahe hain, jisse hum code ke through page change kar sakte hain.


const handleSubmit = async (event) => { // Login form submit hone par ye asynchronous function chalega. 

event.preventDefault(); // Browser ko form submit hone ke baad page refresh karne se rok rahe hain.

   setError(""); // Purana error clear kar rahe hain

    if (!email.trim()) { // Check kar rahe hain ki email empty hai ya nahi

      setError("Email is required"); // Email required ka error message set kar rahe hain

      return; // Function ko yahi stop kar rahe hain
    }

    if (!email.includes("@")) { // Check kar rahe hain ki email me @ hai ya nahi

      setError("Please enter a valid email"); // Invalid email ka error message set kar rahe hain

      return; // Function ko yahi stop kar rahe hain
    }

    if (!password) { // Check kar rahe hain ki password empty hai ya nahi

      setError("Password is required"); // Password required ka error message set kar rahe hain

      return; // Function ko yahi stop kar rahe hain
    }

    if (password.length < 6) { // Check kar rahe hain ki password kam se kam 6 characters ka hai ya nahi

      setError("Password must be at least 6 characters"); // Short password ka error message set kar rahe hain

      return; // Function ko yahi stop kar rahe hain
    }

try { // Login API request ko handle karne ke liye try block start kar rahe hain. 

const loginData = { // Login ke liye required data ka object bana rahe hain.

 email: email, // Email state ki value ko loginData ke email property me store kar rahe hain.
  password: password // Password state ki value ko loginData ke password property me store kar rahe hain.
   }; // loginData object yahan close ho raha hai.


    const response = await loginUser(loginData); // Spring Boot login API ko request bhej rahe hain aur response ka wait kar rahe hain.

     const token = response.data.token; // Backend response se JWT token nikal rahe hain.

      const name = response.data.name; // Backend response se logged-in user ka name nikal rahe hain.


       localStorage.setItem("token", token); // JWT token ko browser ke localStorage me save kar rahe hain.

        localStorage.setItem("name", name); // User ka name bhi localStorage me save kar rahe hain.


         console.log("Login Successful:", response.data); // Backend se aaya complete response browser console me print kar rahe hain.

          alert("Login Successful!"); // User ko login successful hone ka message dikha rahe hain.

          navigate("/home"); // User ko /home URL par le ja rahe hain.x`


           } catch (error) { // Agar login/API request me error aata hai to catch block execute hoga.

            console.error("Login Failed:", error); // Login error ki details browser console me print kar rahe hain.

              setError("Invalid email or password!"); // User ko login failed ka error message dikha rahe hain
              } // try-catch block yahan close ho raha hai.

               }; // handleSubmit function yahan close ho raha hai.


                return ( // Component ka UI/JSX return karna start kar rahe hain.

                 <div className="auth-container"> {/* Login page ke saare elements ko ek parent div ke andar rakh rahe hain. */}
                      {/* Login card */}
      <div className="auth-card">

                  <h1>Login</h1> {/* Page par Login heading display kar rahe hain. */} 

                    {error && ( // Agar error empty nahi hai to error message show hoga
          <p>
            {error} {/* Error state me stored message display kar rahe hain */}
          </p>
        )}


                  <form onSubmit={handleSubmit}> {/* Form submit hone par handleSubmit function call hoga. */} 

                    <input // User ka email enter karne ke liye input field bana rahe hain.
                     type="email" // Input ka type email rakha hai.
                      placeholder="Enter your email" // Input ke andar email ke liye hint text display kar rahe hain.
                       value={email} // Input ki value ko email state ke saath connect kar rahe hain.
                        onChange={(event) => setEmail(event.target.value)} // User jo email type karega usse email state update hogi.
                         />
                         
                         
                          <br /> {/* Next line par jaane ke liye line break laga rahe hain. */}
                          <br /> {/* Email aur password ke beech vertical gap de rahe hain. */}


                           <input // User ka password enter karne ke liye input field bana rahe hain.
                            type="password" // Password ko hidden format me display karne ke liye type password rakha hai.
                             placeholder="Enter your password" // Input ke andar password ke liye hint text display kar rahe hain.
                              value={password} // Input ki value ko password state ke saath connect kar rahe hain.
                               onChange={(event) => setPassword(event.target.value)} // User jo password type karega usse password state update hogi.
                                />
                                

                                 <br /> {/* Next line par jaane ke liye line break laga rahe hain. */}
                                 <br /> {/* Password aur Login button ke beech vertical gap de rahe hain. */}


                                      <button type="submit"> {/* Button click karne par form submit hoga */}
                                        Login
                                      </button>

                                    </form>

                                    <p className="auth-link"> {/* Register page par jaane ka message */}
                                      Don't have an account?{" "} {/* User ko bataya ja raha hai ki account nahi hai */}

                                      <Link to="/register"> {/* Register page ke /register route par le jayega */}
                                        Register
                                      </Link>
                                    </p>
                                           </div>
                                       </div> // Main parent div yahan close ho raha hai.
                                      
                                        ); // return statement yahan close ho raha hai.
                                        } // Login component yahan close ho raha hai.

                                        
                                         export default Login; // Login component ko doosri files me import karne ke liye export kar rahe hain.



// 🔥 Login ka flow

// User Email + Password
//         ↓
//     useState()
//         ↓
//    Login button
//         ↓
//    handleSubmit()
//         ↓
//     loginData {}
//         ↓
//  loginUser(loginData)
//         ↓
//  Axios POST request
//         ↓
// Spring Boot /api/auth/login
//         ↓
//  Backend password verify
//         ↓
//       JWT Token
//         ↓
//  response.data.token
//         ↓
//  localStorage
//         ↓
//     User logged in 🔐
// Sabse important 3 lines
// const response = await loginUser(loginData);

// ➡️ Backend ko login request bhejti hai.

// const token = response.data.token;

// ➡️ Backend se JWT token nikalti hai.

// localStorage.setItem("token", token);

// ➡️ JWT token browser me save karti hai.