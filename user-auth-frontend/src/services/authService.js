import axios from "axios"; // Axios library import kar rahe hain.

// const API_URL = "http://localhost:8080/api/auth"; // Backend API ka base URL store kar rahe hain.
const API_URL = "https://user-auth-backend-3lec.onrender.com/api/auth";

const api = axios.create({ // Axios ka custom instance create kar rahe hain.
    baseURL: API_URL // Har request ke starting me ye base URL automatically use hoga.
});

// =====================================================
// REQUEST INTERCEPTOR
// =====================================================

api.interceptors.request.use( // Request backend par bhejne se pehle interceptor execute hoga.

    (config) => { // Ye function request ko backend par bhejne se pehle execute hoga.

        const token = localStorage.getItem("token"); // Browser ke localStorage se JWT token nikal rahe hain.

        if (token) { // Check kar rahe hain ki JWT token available hai ya nahi.

            config.headers.Authorization = `Bearer ${token}`; // Request ke Authorization header me JWT token add kar rahe hain.
        }

        return config; // Modified request ko backend ki taraf aage bhej rahe hain.
    },

    (error) => { // Request prepare karte waqt error aaye to ye function chalega.

        return Promise.reject(error); // Error ko reject karke calling function tak bhej rahe hain.
    }
);


// =====================================================
// RESPONSE INTERCEPTOR
// =====================================================

api.interceptors.response.use( // Backend se response aane ke baad interceptor chalega.

    (response) => { // Agar backend se successful response aaya.

        return response; // Response ko normally component tak bhej rahe hain.
    },

    (error) => { // Agar backend se error response aaya.

        if (error.response && error.response.status === 401) { // Check kar rahe hain kya status 401 Unauthorized hai.// if (error.response?.status === 401) ye vala code likh rahe the ye isliye nahi likha kyu ki ye ?. ke bich space create kar rha he be faltu me to error aa rahi he  baki jo abhi likha he vo bhi sahi he 

            localStorage.removeItem("token"); // Invalid/expired JWT ko localStorage se delete kar rahe hain.

            localStorage.removeItem("name"); // User ka saved name bhi delete kar rahe hain.

            window.location.href = "/login"; // User ko login page par redirect kar rahe hain.
        }

        return Promise.reject(error); // Error ko calling component tak bhej rahe hain.
    }
);

// =====================================================
// REGISTER API
// =====================================================

export const registerUser = (userData) => { // Registration ke liye registerUser function bana rahe hain.

    return api.post("/register", userData); // Backend ke/register endpoint par userData ke saath POST request bhej rahe hain.
};

export const loginUser = (loginData) => { // Login ke liye loginUser function bana rahe hain.

    return api.post("/login", loginData); // Backend ke /login endpoint par loginData ke saath POST request bhej rahe hain.
};

export default api; // Custom Axios instance ko doosri React files me use karne ke liye export kar rahe hain.