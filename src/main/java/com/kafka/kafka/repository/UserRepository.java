package com.kafka.kafka.repository;

import com.kafka.kafka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    User findByMobileNumber(String mobileNumber);
}
