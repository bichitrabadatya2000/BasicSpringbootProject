package com.kafka.kafka.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetUserDataRequest {

    @NotBlank(message = "Message shouldn't be null or empty ")
    @NotNull(message = "Message shouldn't be null or empty ")
    private String mobileNumber;
}
