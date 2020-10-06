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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.xcasemanager.messenger.web.rest.controller.MessageException;
import org.xcasemanager.messenger.web.rest.controller.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.xcasemanager.messenger.web.rest.controller.MessageException;


@Slf4j
@ControllerAdvice
public class MessageExceptionHandler extends ResponseEntityExceptionHandler 
{
    private String INCORRECT_REQUEST = "INCORRECT_REQUEST";
    private String BAD_REQUEST = "BAD_REQUEST";
    private static final Logger log = LoggerFactory.getLogger(MessageExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleInvalidMessageException
                        (Exception ex, WebRequest request) {
        log.info(" +++++++++---------->> Message error excetion ");
        ErrorResponse error = new ErrorResponse("Validation Failed", null);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(" +++++++++---------->> Message error validation");
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}