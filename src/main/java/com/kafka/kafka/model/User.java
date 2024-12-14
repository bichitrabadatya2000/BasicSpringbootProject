package com.kafka.kafka.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
@Entity

public class User {


    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)

    private String userId;

    private String name;
    private String mobileNumber;
    private String mailId;
    private String passWord;





}
