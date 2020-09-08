package org.xcasemanager.messenger.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecutionMessageDto {

    private String executionId;

    private String result;
}