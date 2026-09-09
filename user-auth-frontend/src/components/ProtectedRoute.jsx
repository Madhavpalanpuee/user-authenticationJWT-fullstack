import { Navigate } from "react-router-dom"; // React Router se Navigate component import kar rahe hain.

function ProtectedRoute({ children }) { // ProtectedRoute component bana rahe hain, children me protected page milega.

  const token = localStorage.getItem("token"); // localStorage se JWT token nikal rahe hain.

  if (!token) { // Check kar rahe hain ki token available hai ya nahi.

    return <Navigate to="/login" replace />; // Token nahi hai to user ko login page par redirect kar rahe hain.
  }

  return children; // Token hai to protected page ko display kar rahe hain.
}

export default ProtectedRoute; // ProtectedRoute component ko export kar rahe hain.






//  Simple flow

// text
// User protected page par gaya
//           ↓
// ProtectedRoute
//           ↓
// localStorage se token
//           ↓
//       Token hai?
//        ↙       ↘
//      NO         YES
//       ↓           ↓
//  /login       children
//                 ↓
//           Protected Page

