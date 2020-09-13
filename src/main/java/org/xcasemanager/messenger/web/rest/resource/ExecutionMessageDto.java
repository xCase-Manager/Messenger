package org.xcasemanager.messenger.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class ExecutionMessageDto {

    @NotNull
    @Min(5)
    private String executionId;

    @NotNull
    @Size(min=2, max=30)
    private String result;
}