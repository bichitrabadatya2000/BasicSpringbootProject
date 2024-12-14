package com.kafka.kafka.dto.response;

import com.kafka.kafka.model.User;
import lombok.Data;

@Data

public class GetUserRegisterResponse {

    private int statusCode;
    private String message;
    private User user;
}
