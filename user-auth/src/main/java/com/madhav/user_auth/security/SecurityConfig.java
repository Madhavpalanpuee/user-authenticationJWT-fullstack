        package com.madhav.user_auth.security; // Ye class security package ke andar hai.

        import org.springframework.context.annotation.Bean; // Method ke return object ko Spring Bean banane ke liye.
        import org.springframework.context.annotation.Configuration; // Spring ko batata hai ki ye configuration class hai.
        import org.springframework.security.config.annotation.web.builders.HttpSecurity; // HTTP security rules configure karne ke liye.
        import org.springframework.security.config.http.SessionCreationPolicy; // Session create karne ki policy set karne ke liye.
        import org.springframework.security.web.SecurityFilterChain; // Spring Security ki filters ki chain ko represent karta hai.
        import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Spring Security ka default username/password authentication filter.

        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // BCrypt algorithm se password hash karne ke liye.
        import org.springframework.security.crypto.password.PasswordEncoder; // PasswordEncoder interface ke liye.

        // ye frontend bana rahe the jb add kiya he 
        import org.springframework.web.cors.CorsConfiguration; // CORS configuration banane ke liye.
        import org.springframework.web.cors.CorsConfigurationSource; // CORS configuration ko Spring Security se connect karne ke liye.
        import org.springframework.web.cors.UrlBasedCorsConfigurationSource; // URL ke basis par CORS configuration apply karne ke liye.

        import java.util.List; // Multiple values ki List banane ke liye.


        @Configuration // Spring ko batata hai ki is class mein security configuration define ki gayi hai.
        public class SecurityConfig { // Application ke security rules configure karne wali class.

        private final JwtAuthenticationFilter jwtAuthenticationFilter; // Hamare custom JWT filter ki dependency/reference.

        public SecurityConfig( // Constructor ke through JwtAuthenticationFilter ko inject kar rahe hain.
                JwtAuthenticationFilter jwtAuthenticationFilter // Spring automatically JwtAuthenticationFilter ka object provide karega.
        ) {

                this.jwtAuthenticationFilter = jwtAuthenticationFilter; // Received filter object ko class variable mein store kar rahe hain.
        }


        @Bean // Is method se return hone wala PasswordEncoder object Spring Bean banega.
        public PasswordEncoder passwordEncoder() { // PasswordEncoder ka object provide karne wala method.

                return new BCryptPasswordEncoder(); // BCryptPasswordEncoder ka object return kar rahe hain.
        }


        @Bean // Is method se return hone wala SecurityFilterChain object Spring Bean banega.
        public SecurityFilterChain securityFilterChain( // Spring Security ki complete security configuration define karne wala method.
                HttpSecurity http // Spring Security ka HttpSecurity object receive kar rahe hain.
        ) throws Exception { // Security configuration ke time Exception aa sakta hai.

                http // HttpSecurity configuration start kar rahe hain.
                        // ye cors vali line bhi frontend ke sath add hui he backend se conect karne ke liye
                        .cors(cors -> cors.configurationSource(corsConfigurationSource())) // React aur Spring Boot ke beech CORS enable kar rahe hain. 1. "Spring Security, CORS ko configure/start kar.". 2.cors ->Lambda ka variable CORS configuration object ko represent karta hai. 3. configurationSource(...) → Spring Security ka method → "configuration do".4. corsConfigurationSource() → tumhara method → "ye lo configuration"

                .csrf(csrf -> csrf.disable()) // REST API ke liye CSRF protection disable kar rahe hain.

                .sessionManagement(session -> // Session management ki configuration start kar rahe hain.
                        session.sessionCreationPolicy( // Session create karne ki policy set kar rahe hain.
                                SessionCreationPolicy.STATELESS // Server session store nahi karega, authentication JWT se hogi.
                        )
                )

                .authorizeHttpRequests(auth -> auth // Different API URLs ke authorization rules configure kar rahe hain.

                        .requestMatchers( // Specific API endpoints select kar rahe hain.
                                "/api/auth/register", // Registration endpoint ko select kar rahe hain.
                                "/api/auth/login" // Login endpoint ko select kar rahe hain.
                        ).permitAll() // In dono endpoints ko bina authentication/JWT ke access karne de rahe hain.

                        .anyRequest().authenticated() // Inke alawa baaki har request ke liye valid authentication required hai.
                )

                .addFilterBefore( // Hamare custom filter ko Spring Security filter chain mein add kar rahe hain.
                        jwtAuthenticationFilter, // Hamara custom JWT authentication filter.
                        UsernamePasswordAuthenticationFilter.class // JWT filter is default filter se pehle execute hoga.
                );

                return http.build(); // Saari security configuration ko build karke SecurityFilterChain return kar rahe hain.
        }

        @Bean // CORS configuration ko Spring Bean bana rahe hain.
        public CorsConfigurationSource corsConfigurationSource() { // CORS configuration provide karne wala method bana rahe hain.

                CorsConfiguration configuration = new CorsConfiguration(); // CORS configuration ka object create kar rahe hain.


                configuration.setAllowedOrigins( // Kaunse frontend origins backend ko access kar sakte hain ye define kar rahe hain.
                        List.of("http://localhost:5174",// Hamare React/Vite frontend ko backend access karne ki permission de rahe hain.
                                "http://localhost:3000", // ye jo localhost:3000 he dokar ka he yaad rakh na dokar prr chalane ke liye
                                "https://user-authentication-jwt-fullstack.vercel.app")  
                );


                configuration.setAllowedMethods( // Frontend se kaunse HTTP methods allow honge ye define kar rahe hain.
                        List.of("GET", "POST", "PUT", "DELETE", "OPTIONS") // Ye HTTP methods allow kar rahe hain.
                );


                configuration.setAllowedHeaders( // Frontend request ke andar kaunse headers allowed hain ye define kar rahe hain.
                        List.of("*") // "*" ka matlab request ke saare headers allow hain.
                );


                configuration.setExposedHeaders( // Backend response ke kaunse headers frontend access kar sakta hai ye define kar rahe hain.
                        List.of("Authorization") // Authorization header ko frontend ke liye accessible bana rahe hain.
                );


                UrlBasedCorsConfigurationSource source = // URL-based CORS configuration source ka object bana rahe hain.
                        new UrlBasedCorsConfigurationSource(); // Naya configuration source object create kar rahe hain.


                source.registerCorsConfiguration( // CORS configuration ko URL pattern ke saath register kar rahe hain.
                        "/**", // "/**" ka matlab backend ke saare endpoints par CORS apply hoga.
                        configuration // Upar banayi hui CORS configuration ko apply kar rahe hain.
                );


                return source; // Configured CORS source ko Spring Security ko return kar rahe hain.
        }
        }