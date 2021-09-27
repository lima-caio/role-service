package com.marvin.roleservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marvin.roleservice.domain.Role;
import com.marvin.roleservice.validation.annotation.Uuid;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Data transfer object {@link Role}.
 */
@Data
@JsonInclude(NON_NULL)
public class RoleDto {

    @Uuid
    @ApiModelProperty(value = "roleId", position = 1)
    private String roleId;

    @ApiModelProperty(value = "name", position = 2)
    private String name;

    @ApiModelProperty(value = "description", position = 3)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
