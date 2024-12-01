package com.jwt.example.controller;

import com.jwt.example.service.LoginService;
import com.jwt.example.service.UserService;
import com.jwt.example.util.JwtUtil;
import com.jwt.example.util.LoginRequest;
import com.jwt.example.util.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;
/*
    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;


    @Autowired
    AuthenticationManager authenticationManager;
   */

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody LoginRequest request){
        return loginService.authenticate(request);
    }

    /*@PostMapping("/login")
    private Object authenticateUser(@RequestBody LoginRequest loginDTO){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getEmailId(),
                    loginDTO.getPassword()
            ));

            UserDetails userDetails = userService.loadUserByUsername(loginDTO.getEmailId());
            String jwt = jwtUtil.generateToken(userDetails);

            Map<String, Object> response = new HashMap<>();
            response.put("jwt_token", jwt);
            response.put("status", HttpStatus.OK);

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }*/
}
