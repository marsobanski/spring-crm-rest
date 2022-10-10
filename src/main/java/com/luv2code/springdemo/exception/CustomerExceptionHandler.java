package com.luv2code.springdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //dzięki temu metody z @ExceptionHandler z tej klasy są widoczne w całej aplikacji
                    // i są w stanie wszędzie przechwycić wyjątek z metod z @RequestMapping
public class CustomerExceptionHandler {

    @ExceptionHandler //metoda do przechwycania CustomerNotFoundException
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exception) {
        CustomerErrorResponse response = new CustomerErrorResponse();
        response.setMesssage(exception.getMessage());
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler //metoda do przechwycania Exception
    public ResponseEntity<CustomerErrorResponse> handleException(Exception exception) {
        CustomerErrorResponse response = new CustomerErrorResponse();
        response.setMesssage(exception.getMessage());
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
