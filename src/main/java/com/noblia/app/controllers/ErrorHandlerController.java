package com.noblia.app.controllers;

import java.util.HashMap;
import java.util.Map;

import com.noblia.app.exceptions.TitleNotValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(value = IllegalArgumentException.class)
    private ResponseEntity<Map<String, Object>> IllegalArgumentHandler(IllegalArgumentException e) {
        final var response = new HashMap<String, Object>();
        
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("status", HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.put("message", e.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = TitleNotValueException.class)
    private ResponseEntity<Map<String, Object>> TitleNotValueExceptionHandler(TitleNotValueException e) {
        final var response = new HashMap<String, Object>();

        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("status", HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.put("message", e.getMessage());

        return ResponseEntity.badRequest().body(response);
    }
}
