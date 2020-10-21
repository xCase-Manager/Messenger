package org.xcasemanager.messenger.web.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.FieldError;


@Slf4j
@ControllerAdvice
public class MessageExceptionHandler extends ResponseEntityExceptionHandler 
{
    private static final Logger log = LoggerFactory.getLogger(MessageExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex, 
    HttpHeaders headers, 
    HttpStatus status, 
    WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        
        ApiError apiError = 
        new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        log.error("Web request error: %s", ex.getLocalizedMessage());    
        return handleExceptionInternal(
        ex, apiError, headers, apiError.getStatus(), request);
    }
}