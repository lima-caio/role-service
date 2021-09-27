package com.marvin.roleservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Response Error data transfer object.
 */
@Data
@Builder
@JsonInclude(NON_NULL)
public class ResponseErrorDto {
    @ApiModelProperty(value = "error message", position = 1)
    private String message;
}
