package com.dinelink.configurations;

import com.dinelink.services.ModeratorDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import org.springframework.web.cors.CorsConfigurationSource;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private ModeratorDetailsService moderatorDetailsService;

    private CorsConfigurationSource corsConfigurationSource;

    @Autowired
    public SecurityConfiguration(ModeratorDetailsService moderatorDetailsService, CorsConfigurationSource corsConfigurationSource) {
        this.moderatorDetailsService = moderatorDetailsService;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource))  // Configure CORS if needed
                .csrf(csrf -> csrf.disable())  // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/login", "/register", "/forgotpwd").permitAll()
                        .requestMatchers("profile/**", "profile", "personalDetails", "profilePic", "address", "education", "mediaLinks")
                        .hasAuthority("ROLE_CHEF") // Use hasAuthority instead of hasRole
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/api/v1/moderator")
                        .hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .httpBasic(withDefaults()); // Enable Basic Authentication

        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(moderatorDetailsService);
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}