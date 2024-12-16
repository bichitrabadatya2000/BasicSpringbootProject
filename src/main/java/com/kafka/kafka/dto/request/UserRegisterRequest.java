package com.kafka.kafka.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class UserRegisterRequest {



    @NotNull(message = "Name shouldn't be null ")
    private String name;
    @NotBlank(message = "MobileNumber shouldn't be blank ")
    private String mobileNumber;
    @NotBlank(message = "EmailId shouldn't blank ")
    private String mailId;
    @NotBlank(message = "Password shouldn't blank ")
    private String passWord;
}
