import { useNavigate } from "react-router-dom"; // React Router se useNavigate import kar rahe hain

function Navbar() {

  const navigate = useNavigate(); // Page ko programmatically change karne ke liye useNavigate use kar rahe hain

  const name = localStorage.getItem("name"); // localStorage se logged-in user ka name nikal rahe hain

  const handleLogout = () => { // Logout button click hone par ye function chalega

    localStorage.removeItem("token"); // JWT token ko localStorage se remove kar rahe hain

    localStorage.removeItem("name"); // User ka name bhi localStorage se remove kar rahe hain

    navigate("/login"); // Logout ke baad login page par bhej rahe hain
  };

  return (
     // Navbar
    <nav className="navbar">

      <h2>User Auth App</h2> {/* Application ka naam */}

      <span>Welcome, {name}</span> {/* Logged-in user ka naam */}

      <button onClick={handleLogout}> {/* Logout button par handleLogout function laga rahe hain */}
        Logout
      </button>

    </nav>
  );
}

export default Navbar; // Navbar component export kar rahe hain


// Logout
//  ↓
// removeItem("token")
//  ↓
// removeItem("name")
//  ↓
// /login