package com.jwt.example.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String jwtToken;

    public LoginResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
