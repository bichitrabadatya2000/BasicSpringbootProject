package com.kafka.kafka.serviceImpl;

import com.kafka.kafka.dto.request.GetUserDataRequest;
import com.kafka.kafka.dto.request.UserRegisterRequest;
import com.kafka.kafka.dto.response.GetUserRegisterResponse;
import com.kafka.kafka.dto.response.UserRegisterResponse;
import com.kafka.kafka.exception.NotFoundExceptions;
import com.kafka.kafka.model.User;
import com.kafka.kafka.repository.UserRepository;
import com.kafka.kafka.service.UserRegisterService;
import com.kafka.kafka.utility.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;


@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncryption passwordEncryption;


    @Override
    public UserRegisterResponse userRegister(UserRegisterRequest userRegisterRequest) throws Exception, NotFoundExceptions {
        UserRegisterResponse userRegisterResponse=new UserRegisterResponse();
        if (userRegisterRequest.getMobileNumber()!=null){
            User user=userRepository.findByMailId(userRegisterRequest.getMailId());
            if (user!=null){
               throw new NotFoundExceptions("You are already registered Kindly sign-in ");
            }
        }
        User user = new User();
        user.setName(userRegisterRequest.getName());
        user.setMailId(userRegisterRequest.getMailId());
        user.setMobileNumber(userRegisterRequest.getMobileNumber());
        SecretKey secretKey=PasswordEncryption.getStaticKey();
        user.setPassWord(PasswordEncryption.encrypt(userRegisterRequest.getPassWord(),secretKey));
        userRepository.save(user);
        userRegisterResponse.setStatusCode(200);
        userRegisterResponse.setMessage("Data fetched successFully");
        return userRegisterResponse;
    }

    @Override
    public GetUserRegisterResponse getUserData(GetUserDataRequest getUserDataRequest) throws NotFoundExceptions {
        GetUserRegisterResponse getUserRegisterResponse=new GetUserRegisterResponse();
        if (getUserDataRequest.getMailId()!=null) {
            User user=userRepository.findByMailId(getUserDataRequest.getMailId());

            getUserRegisterResponse.setStatusCode(200);
            getUserRegisterResponse.setMessage("Data fetched successFully");
            getUserRegisterResponse.setUser(user);

//            Optional<User> user = Optional.ofNullable(userRepository.findByMobileNumber(getUserDataRequest.getMobileNumber()).
//                    orElseThrow(() -> new NotFoundExceptions("Employee not found")));

        }
        return getUserRegisterResponse;
    }
}
