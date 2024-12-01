package com.jwt.example.service;

import com.jwt.example.util.JwtUtil;
import com.jwt.example.util.LoginRequest;
import com.jwt.example.util.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;


    public ResponseEntity<Object> authenticate(LoginRequest request){

        try{
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    request.getEmailId(),
                    request.getPassword()
            );
            authenticationManager.authenticate(authenticationToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmailId());



            String jwtToken = jwtUtil.generateToken(userDetails);
            System.out.println("Token :" + jwtToken);
            LoginResponse response = new LoginResponse(jwtToken);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("User Does not exists");
        }

    }


}
