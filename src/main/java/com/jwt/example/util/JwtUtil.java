package com.jwt.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class JwtUtil {

    private final static String SECRET_KEY = "mysecretKeymySecretKeymysecretKeymySecretKeymysecretKeymySecretKey";

    private final static long EXPIRATION_DATE = 2 * 60 * 1000;

    //Decode the token
    public Claims extractAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUserNameFromToken(String token){
        final Claims claims = extractAllClaimsFromToken(token);
        return claims.getSubject();
    }

    public Date extractExpirationFromToken(String token){
        final Claims claims = extractAllClaimsFromToken(token);
        return claims.getExpiration();
    }

    //Encode the token
    public String generateToken(UserDetails userDetails){

//        Map<String, Object> roles = new HashMap<>();
//        roles.put("roles", userDetails.getAuthorities());
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE ))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String username = extractUserNameFromToken(token);
        Date expirationDate = extractExpirationFromToken(token);
        return username.equals(userDetails.getUsername()) && expirationDate.after(new Date());
    }

/*    public static void main(String[] args) {
        UserDetails user = new User("rohit",
                "rohit123",
                Set.of(new SimpleGrantedAuthority("ADMIN")));

        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken(user);

        System.err.println("Token = " + token);
        System.err.println("Extract Username =" + jwtUtil.extractUserNameFromToken(token));
        System.err.println("Extract Expiration = " + jwtUtil.extractExpirationFromToken(token));
        System.err.println("Validate Token = " + jwtUtil.validateToken(token, user) );
    }*/
}
