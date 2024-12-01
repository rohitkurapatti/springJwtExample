package com.jwt.example.controller;

import com.jwt.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/hello")
    public String getHello(@RequestHeader("Authorization") String authHeader){
        String username = jwtUtil.extractUserNameFromToken(authHeader.substring(7));
        return "System says hello to " + username;
    }

}
