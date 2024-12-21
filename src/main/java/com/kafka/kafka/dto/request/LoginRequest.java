package com.kafka.kafka.dto.request;


import lombok.Data;

@Data
public class LoginRequest {

    private String userName;
    private String passWord;
    private String mobileNumber;
}
