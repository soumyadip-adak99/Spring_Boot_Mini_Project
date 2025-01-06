package net.soumya.JournalApp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for Spring Security.
 * This class defines the security settings for the application, including
 * authentication, authorization, and password encoding mechanisms.
 */
@Configuration
public class SpringSecurity {

    /**
     * Configures the security filter chain for handling HTTP security.
     * Defines which endpoints require authentication and which are publicly accessible.
     *
     * @param http HttpSecurity object for configuring security.
     * @return A configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Disable CSRF (Cross-Site Request Forgery) protection for simplicity.
                // Should be enabled and configured in production for better security.
                .csrf(AbstractHttpConfigurer::disable)

                // Define authorization rules.
                .authorizeHttpRequests(auth -> auth
                        // Restrict access to "/journal/**" and "/users/**" to authenticated users only.
                        .requestMatchers("/journal/**", "/users/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Allow access to all other endpoints without authentication.
                        .anyRequest().permitAll()
                )

                // Enable HTTP Basic authentication for simplicity.
                .httpBasic()

                // Finalize and build the security filter chain.
                .and()
                .build();
    }

    /**
     * Defines the password encoder bean used for encoding and verifying passwords.
     * BCryptPasswordEncoder is used for strong, secure password hashing.
     *
     * @return A PasswordEncoder instance for encoding passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes the AuthenticationManager as a bean.
     * The AuthenticationManager is used to authenticate users based on their credentials.
     *
     * @param authenticationConfiguration Configuration for authentication.
     * @return An AuthenticationManager instance.
     * @throws Exception If an error occurs while retrieving the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

