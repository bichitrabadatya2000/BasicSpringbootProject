package com.kafka.kafka.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusResponse {

    private int statusCode;
    private String message;

}
