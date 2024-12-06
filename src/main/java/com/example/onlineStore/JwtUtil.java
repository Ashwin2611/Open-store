package com.example.onlineStore;

import io.jsonwebtoken.*;

import org.springframework.beans.factory.annotation.Value;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
 
import java.util.Date;
import java.util.function.Function;
 
@Component
public class JwtUtil {
 
//    private String secretKey = "your_secret_key"; // Replace with your own secret key
    @Value("${jwt.secret}")
    private String secretKey;
 
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
 
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
 
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        System.out.println("Claims: " + claims);
        return claimsResolver.apply(claims);
    }
 
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        
    }
 
    private Boolean isTokenExpired(String token) {
        try {
            Date expiration = extractExpiration(token);
            System.out.println("Token Expiration: " + expiration);
            System.out.println("Current Time: " + new Date());
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT: " + e.getMessage());
            return true;
        }
    }
 
    public Boolean validateToken(String token, String username) {
        System.out.println("Validating token...");
        final String extractedUsername = extractUsername(token);
        System.out.println("Extracted Username: " + extractedUsername);
        System.out.println("Input Username: " + username);

        boolean isValid = (extractedUsername.equals(username) && !isTokenExpired(token));
        System.out.println("Is Token Valid: " + isValid);
        return isValid;
    }
 
    public String generateToken(String username) {
        Date now = new Date();
        Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10); // 10 hours
        System.out.println("Current Time: " + now);
        System.out.println("Expiration Time: " + expirationDate);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate) // 10 hours
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
