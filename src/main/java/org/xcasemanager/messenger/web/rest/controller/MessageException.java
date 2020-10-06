package org.xcasemanager.messenger.web.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MessageException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MessageException.class);
 
    public MessageException(String message) {
        super(message);
        log.info(" +++++++++---------->> Message error: " + message);
    }
}