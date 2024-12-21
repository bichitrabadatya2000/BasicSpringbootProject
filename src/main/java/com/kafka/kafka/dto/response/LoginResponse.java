package com.kafka.kafka.dto.response;


import lombok.Data;

@Data
public class LoginResponse {

    private int statusCode;
    private String message;
    private String token;
}
