package com.kafka.kafka.service;


import com.kafka.kafka.dto.request.UserRegisterRequest;
import com.kafka.kafka.dto.response.UserRegisterResponse;
import com.kafka.kafka.exception.NotFoundExceptions;
import com.kafka.kafka.model.User;
import com.kafka.kafka.repository.UserRepository;
import com.kafka.kafka.serviceImpl.UserRegisterServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRegisterServiceImpl userRegisterService; // Replace with your concrete implementation

    @Test
    void testUserRegister_MobileNumberIsNull() throws Exception, NotFoundExceptions {
        // Arrange
        UserRegisterRequest request = new UserRegisterRequest();
        request.setName("John Doe");
        request.setMailId("john.doe@example.com");
        request.setPassWord("password123");
        request.setMobileNumber("7377195331");

        // Act
        UserRegisterResponse response = userRegisterService.userRegister(request);

        // Assert
        assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode(), "Status code should be 200");


       // assertEquals("Data fetched successFully", response.getMessage());
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    void testUserRegister_UserAlreadyRegistered() {
        // Arrange
        UserRegisterRequest request = new UserRegisterRequest();
        request.setMobileNumber("1234567890");

        User existingUser = new User();
        existingUser.setMobileNumber("1234567890");

        Mockito.when(userRepository.findByMobileNumber("1234567890")).thenReturn(existingUser);

        // Act & Assert
        NotFoundExceptions exception = assertThrows(NotFoundExceptions.class, () -> userRegisterService.userRegister(request));
        Assertions.assertEquals("You are already registered Kindly sign-in ", exception.getMessage());
    }

    @Test
    void testUserRegister_SuccessfulRegistration() throws Exception, NotFoundExceptions {
        // Arrange
        UserRegisterRequest request = new UserRegisterRequest();
        request.setName("John Doe");
        request.setMailId("john.doe@example.com");
        request.setMobileNumber("1234567890");
        request.setPassWord("password123");

        Mockito.when(userRepository.findByMobileNumber("1234567890")).thenReturn(null);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UserRegisterResponse response = userRegisterService.userRegister(request);

        // Assert
        assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals("Data fetched successFully", response.getMessage());

        // Verify the repository was called with the correct data
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }



}
