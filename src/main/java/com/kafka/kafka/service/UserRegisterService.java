package com.kafka.kafka.service;

import com.kafka.kafka.dto.request.GetUserDataRequest;
import com.kafka.kafka.dto.request.UserRegisterRequest;
import com.kafka.kafka.dto.response.GetUserRegisterResponse;
import com.kafka.kafka.dto.response.UserRegisterResponse;
import com.kafka.kafka.exception.NotFoundExceptions;

public interface UserRegisterService {


    public UserRegisterResponse userRegister(UserRegisterRequest userRegisterRequest) throws Exception, NotFoundExceptions;
    public GetUserRegisterResponse getUserData(GetUserDataRequest getUserDataRequest) throws NotFoundExceptions;
}
