package com.example.onlineStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Log request headers to verify if Authorization header is present
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Authorization Header: " + request.getHeader("Authorization"));

        String requestURI = request.getRequestURI();
        System.out.println("Request URL: " + requestURI);
        // Skip JWT validation for public endpoints
        if (requestURI.equals("/auth/authenticate") || requestURI.equals("/user/addUser") || requestURI.equals("/user/getAllUsers")) {
            System.out.println("Skipping JWT validation for: " + requestURI);
            chain.doFilter(request, response);
            return;
        }
        
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;  // Example JWT

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            System.out.println("Token found in the Authorization header");
            jwt = authorizationHeader.substring(7);  // Remove "Bearer " prefix
            username = jwtUtil.extractUsername(jwt);
        } else {
            System.out.println("Authorization header missing or invalid");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("Validating token for username: " + username);
            if (jwtUtil.validateToken(jwt, username)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        username, null, null);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                System.out.println("Token validated and authentication set");
            } else {
                System.out.println("Token validation failed");
            }
        }
        
        chain.doFilter(request, response);  // Continue with the request
    }

}
