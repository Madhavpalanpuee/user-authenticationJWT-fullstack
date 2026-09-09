package com.madhav.user_auth.security; // Ye batata hai ki JwtUtil class security package ke andar hai.

import io.jsonwebtoken.Claims; // JWT token ke andar stored information/claims ko access karne ke liye import kar rahe hain.
import io.jsonwebtoken.Jwts; // JWT token generate aur parse/verify karne ke liye Jwts class import kar rahe hain.
import io.jsonwebtoken.security.Keys; // Secret String se SecretKey banane ke liye Keys class import kar rahe hain.
import org.springframework.stereotype.Component; // @Component annotation use karne ke liye import kar rahe hain.

import javax.crypto.SecretKey; // Cryptographic SecretKey object use karne ke liye import kar rahe hain.
import java.nio.charset.StandardCharsets; // String ko UTF-8 bytes mein convert karne ke liye import kar rahe hain.
import java.util.Date; // Token ki creation aur expiration date ke liye Date class import kar rahe hain.


@Component // Spring JwtUtil ka object automatically create aur manage karega.
public class JwtUtil { // JWT related operations handle karne wali utility class create kar rahe hain.

    // secret key toh ye vali hi he baki git hub pr upload kar rahe he toh sequer rakhna jaru ri he isliye apn ye use kar rahe he niche vala 

   // private final String SECRET_KEY = // JWT ko sign aur baad mein verify karne ke liye secret key define kar rahe hain.
   //         "MadhavSecretKeyForJwtAuthenticationProject2026"; // Ye same secret key token generate aur verify dono time use hogi.

   //cmd me ye chalye ge ab jb change hogi-> setx JWT_SECRET "MadhavSecretKeyForJwtAuthenticationProject2026"
   private final String SECRET_KEY = System.getenv("JWT_SECRET");

    private final long EXPIRATION_TIME = // Token kitne time tak valid rahega wo define kar rahe hain.
            1000 * 60 * 60; // Token ki expiry 1 hour set kar rahe hain.


    private SecretKey getSigningKey() { // Secret String ko SecretKey object mein convert karne wala private method hai.

        return Keys.hmacShaKeyFor( // Secret key ke bytes se HMAC signing key create kar rahe hain.
                SECRET_KEY.getBytes(StandardCharsets.UTF_8) // Secret String ko UTF-8 byte array mein convert kar rahe hain.
        );
    }


    public String generateToken(String email) { // User ka email lekar JWT token generate karega aur String return karega.

        return Jwts.builder() // JWT token banana start kar rahe hain.

                .subject(email) // User ka email JWT ke subject ke andar store kar rahe hain.

                .issuedAt(new Date()) // Token kis current time par create hua wo store kar rahe hain.

                .expiration( // Token ki expiration date/time set karna start kar rahe hain.
                        new Date( // Expiration ke liye Date object create kar rahe hain.
                                System.currentTimeMillis() // Current system time milliseconds mein le rahe hain.
                                        + EXPIRATION_TIME // Current time mein 1 hour add kar rahe hain.
                        )
                )

                .signWith(getSigningKey()) // JWT ko hamari secret signing key se sign kar rahe hain.

                .compact(); // Final JWT ko compact String format mein generate karke return kar rahe hain.
    }


    public String extractEmail(String token) { // JWT token lekar uske andar stored email/subject return karega.

        return getClaims(token).getSubject(); // Token ke claims se subject nikal rahe hain, jisme humne email store kiya tha.
    }


    private Claims getClaims(String token) { // JWT token ko parse aur verify karke uske claims return karne wala private method hai.

        return Jwts.parser() // JWT parser create karna start kar rahe hain.

                .verifyWith(getSigningKey()) // Token ki signature ko same secret key se verify kar rahe hain.

                .build() // Parser ko build karke ready kar rahe hain.

                .parseSignedClaims(token) // JWT token ko parse aur signature verify kar rahe hain.

                .getPayload(); // Token ke andar stored claims/payload return kar rahe hain.
    }


    public boolean isTokenValid(String token) { // JWT token valid hai ya nahi check karke true ya false return karega.

        try { // Invalid ya expired token se aane wale exception ko handle karne ke liye try block use kar rahe hain.

            Claims claims = getClaims(token); // Token ko parse karke uske claims nikal rahe hain.

            return claims.getExpiration().after(new Date()); // Check kar rahe hain ki token ki expiry current time ke baad hai ya nahi.

        } catch (Exception e) { // Agar token invalid, expired ya tampered hai to exception handle hoga.

            return false; // Koi problem aane par token ko invalid maan kar false return kar rahe hain.
        }
    }
}





// yha abhi tak token hi genret ho rha tha ab jo uppr code likha he hum he usme gerrate and valid dono ho ga tokan 


// package com.madhav.user_auth.security; // Ye batata hai ki JwtUtil class kis package ke andar hai.

// import io.jsonwebtoken.Jwts; // JWT token create aur parse karne ke liye JJWT library import kar rahe hain.

// import io.jsonwebtoken.security.Keys; // Secret key generate karne ke liye Keys class import kar rahe hain.

// import org.springframework.stereotype.Component; // @Component annotation import kar rahe hain.

// import javax.crypto.SecretKey; // SecretKey type use karne ke liye import kar rahe hain.

// import java.nio.charset.StandardCharsets; // String ko UTF-8 bytes mein convert karne ke liye import kar rahe hain.

// import java.util.Date; // Current date aur expiry date ke liye Date class import kar rahe hain.


// @Component // Spring is class ka object automatically create karega aur manage karega.
// public class JwtUtil { // JwtUtil naam ki utility class create ho rahi hai.

//     private final String SECRET_KEY = // JWT sign karne ke liye secret key define kar rahe hain.
//             "MadhavSecretKeyForJwtAuthenticationProject2026"; // Ye secret string JWT generate aur verify karne ke kaam aayegi.

//     private final long EXPIRATION_TIME = // Token kitni der tak valid rahega uska time define kar rahe hain.
//             1000 * 60 * 60; // 1000 ms × 60 sec × 60 min = 1 hour.

//     private SecretKey getSigningKey() { // Secret key generate karne wala method.

//         return Keys.hmacShaKeyFor( // Secret string ko SecretKey object mein convert kar rahe hain.
//                 SECRET_KEY.getBytes(StandardCharsets.UTF_8) // Secret string ko UTF-8 bytes mein convert kar rahe hain.
//         );
//     }

//     public String generateToken(String email) { // Email lekar JWT token generate karega aur String return karega.

//         return Jwts.builder() // JWT token banana start kar rahe hain.

//                 .subject(email) // User ka email token ke subject ke roop mein store kar rahe hain.

//                 .issuedAt(new Date()) // Token kab generate hua uska current time set kar rahe hain.

//                 .expiration( // Token ki expiry date set kar rahe hain.
//                         new Date(System.currentTimeMillis() // Current system time le rahe hain.
//                                 + EXPIRATION_TIME) // Current time mein 1 hour add kar rahe hain.
//                 )

//                 .signWith(getSigningKey()) // Token ko secret key se digitally sign kar rahe hain.

//                 .compact(); // JWT object ko final String token mein convert kar rahe hain.
//     }
// }