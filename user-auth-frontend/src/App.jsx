import { BrowserRouter, Routes, Route } from "react-router-dom"; // React Router se BrowserRouter, Routes aur Route import kar rahe hain.

import Login from "./pages/Login"; // Login page component import kar rahe hain.
import Register from "./pages/Register"; // Register page component import kar rahe hain.
import Home from "./pages/Home"; // Home page component import kar rahe hain.

// ProtectedRoute import
import ProtectedRoute from "./components/ProtectedRoute";


function App() { // App naam ka main React functional component bana rahe hain.

  return ( // App component ka UI return karna start kar rahe hain.

    <BrowserRouter> {/* Puri application me routing enable kar rahe hain. */}

      <Routes> {/* Application ke saare routes yahan define karenge. */}

        <Route path="/login" element={<Login />} /> {/* /login URL par Login component display hoga. */}

        <Route path="/register" element={<Register />} /> {/* /register URL par Register component display hoga. */}

        <Route path="/home" element={
            // Home ko ProtectedRoute ke andar wrap kar rahe hain
            <ProtectedRoute>
              {/* Token hone par hi Home render hoga */}
              <Home />
            </ProtectedRoute>
          } /> {/* /home URL par Home component display hoga. */}

      </Routes> {/* Routes yahan close ho raha hai. */}

    </BrowserRouter> // BrowserRouter yahan close ho raha hai.

  ); // return statement yahan close ho raha hai.
} // App component yahan close ho raha hai.


export default App; // App component ko export kar rahe hain.