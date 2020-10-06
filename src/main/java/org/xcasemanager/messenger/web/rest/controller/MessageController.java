package org.xcasemanager.messenger.web.rest.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xcasemanager.messenger.service.MessageService;
import org.xcasemanager.messenger.web.rest.service.ExecutionPublisher;
import org.xcasemanager.messenger.web.rest.resource.ExecutionMessageDto;
import javax.validation.Valid;

@RestController
@RequestMapping("v1/message")
public class MessageController {

    private static final int SWAGGER_HTTP_STATUS_CREATED = 202;

    @Autowired
    private ExecutionPublisher messageService;

    @ApiOperation(value = "addMessage", nickname = "addMessage")
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {@ApiResponse(code = SWAGGER_HTTP_STATUS_CREATED, message = "Created")})
    public ResponseEntity addMessage(final @Valid @RequestBody ExecutionMessageDto executionMessageDto) {
        messageService.addMessage(executionMessageDto);  
        return ResponseEntity.accepted().body(executionMessageDto);    
    }
}