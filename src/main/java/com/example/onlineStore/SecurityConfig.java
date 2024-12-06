package com.example.onlineStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;  // Inject JwtRequestFilter

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Ashwin");
        http.cors() // Enable CORS
        .and().csrf().disable()
            .authorizeRequests()
            .requestMatchers("/user/addUser","/auth/authenticate","/user/getAllUsers","/error").permitAll() // Public endpoints
            .anyRequest().authenticated() // Protect all other endpoints
            .and()
            .httpBasic(); // Or customize authentication as needed

        // Add JwtRequestFilter to the filter chain before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Bean for AuthenticationManager if needed (for custom authentication)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
