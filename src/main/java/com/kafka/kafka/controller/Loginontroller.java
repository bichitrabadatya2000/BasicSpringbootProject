package com.kafka.kafka.controller;


import com.kafka.kafka.dto.request.LoginRequest;
import com.kafka.kafka.dto.response.LoginResponse;
import com.kafka.kafka.exception.NotFoundExceptions;
import com.kafka.kafka.model.User;
import com.kafka.kafka.repository.UserRepository;
import com.kafka.kafka.utility.JwtService;
import com.kafka.kafka.utility.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/user")
public class Loginontroller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    //localhost:8080/user/login


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws NotFoundExceptions, Exception {
        LoginResponse loginResponse=new LoginResponse();

        User user=userRepository.findByMailId(loginRequest.getUserName());
        if (user==null){
            throw new NotFoundExceptions("You have provided mail which is not in our system kindly sign-up");
        }
        SecretKey secretKey=PasswordEncryption.getStaticKey();
        if (!PasswordEncryption.decrypt(user.getPassWord(),secretKey).equals(loginRequest.getPassWord())){
            throw new NotFoundExceptions("You have entered wrong password kindly retry");
        }

        String userName= loginRequest.getUserName()+loginRequest.getMobileNumber();
        String token= jwtService.generateToken(userName,loginRequest.getUserName());
        loginResponse.setStatusCode(200);
        loginResponse.setMessage("Login");
        loginResponse.setToken(token);

        return loginResponse;

    }

}
