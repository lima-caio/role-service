package com.marvin.roleservice.rest.dto;

import com.marvin.roleservice.domain.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Data transfer object for {@link Role} creation.
 */
@Data
public class RoleCreationDto {

    @ApiModelProperty(value = "name", position = 1)
    private String name;

    @ApiModelProperty(value = "description", position = 2)
    private String description;
}
