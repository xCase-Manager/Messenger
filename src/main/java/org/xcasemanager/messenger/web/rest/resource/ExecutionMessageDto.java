package org.xcasemanager.messenger.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.AssertFalse;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class ExecutionMessageDto {

    @NotNull
    @Min(1)
    public Long executionId;

    @NotNull
    @Size(min=5, max=30)
    public String status;

    @Size(max=50)
    public String result;
    
    @JsonIgnore
    @AssertFalse(message = "a result is is required")
    private boolean resultErrorExist() {
        return (this.status.equals("completed") && this.result == null);
    }
}