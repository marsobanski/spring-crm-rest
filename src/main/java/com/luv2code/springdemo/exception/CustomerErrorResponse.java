package com.luv2code.springdemo.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerErrorResponse {
    private int statusCode;
    private String messsage;
    private long timestamp;
}

