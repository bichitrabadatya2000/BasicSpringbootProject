package com.kafka.kafka.controller;


import com.kafka.kafka.dto.request.GetUserDataRequest;
import com.kafka.kafka.dto.request.UserRegisterRequest;
import com.kafka.kafka.dto.response.GetUserRegisterResponse;
import com.kafka.kafka.dto.response.UserRegisterResponse;
import com.kafka.kafka.exception.NotFoundExceptions;
import com.kafka.kafka.service.UserRegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRegisterController {

    @Autowired
   private UserRegisterService userRegisterService;


   @GetMapping("/getData")
   public String getData(){
       return "Hello";
   }

   @PostMapping("/register")
   public UserRegisterResponse userRegister(@Valid @RequestBody UserRegisterRequest userRegisterRequest) throws NotFoundExceptions {
       UserRegisterResponse userRegisterResponse=new UserRegisterResponse();
       try {
           userRegisterResponse=userRegisterService.userRegister(userRegisterRequest);
       }catch (Exception e){
           throw new NotFoundExceptions(e.getMessage());
       }
       return userRegisterResponse;
   }


    @GetMapping("/get/userData")
    public GetUserRegisterResponse getUserData(@RequestBody  GetUserDataRequest getUserDataRequest) throws NotFoundExceptions {
        GetUserRegisterResponse getUserRegisterResponse=new GetUserRegisterResponse();
        try {
            getUserRegisterResponse=userRegisterService.getUserData(getUserDataRequest);
        }catch (Exception e){
            throw new NotFoundExceptions(e.getMessage());
        }

        return getUserRegisterResponse;
    }
}
