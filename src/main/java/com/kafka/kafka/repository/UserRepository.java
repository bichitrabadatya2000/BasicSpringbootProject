package com.kafka.kafka.repository;

import com.kafka.kafka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository extends JpaRepository<User,Long> {

    User findByMailId(String mailId);
}
