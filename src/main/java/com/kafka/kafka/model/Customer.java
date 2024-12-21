package com.kafka.kafka.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Customer")
@Data

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String price;


}
