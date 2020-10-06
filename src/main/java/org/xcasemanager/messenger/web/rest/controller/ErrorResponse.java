package org.xcasemanager.messenger.web.rest.controller;

import java.util.List;
 
public class ErrorResponse
{
    private String message;
    private List<String> details;
    
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
 
    public String getMessage() {
        return this.message;
    }

    public List<String> getDetails() {
        return this.details;
    }
}