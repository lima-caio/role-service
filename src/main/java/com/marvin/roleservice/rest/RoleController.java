package com.marvin.roleservice.rest;

import com.marvin.roleservice.domain.Role;
import com.marvin.roleservice.rest.dto.ResponseErrorDto;
import com.marvin.roleservice.rest.dto.RoleCreationDto;
import com.marvin.roleservice.rest.dto.RoleDto;
import com.marvin.roleservice.service.RoleService;
import com.marvin.roleservice.mapping.RoleMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller para {@link Role}.
 */
@Api(tags = "Role Controller")
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class RoleController {

    private static final String ROLE_ID = "roleId";

    private static final String OK_STATUS = "Ok";
    private static final String BAD_REQUEST = "Bad Request";
    private static final String CREATED = "Created";
    private static final String UNSUPPORTED_MEDIA_TYPE = "Unsupported Media Type";
    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    /**
     * Creates a new {@link Role}.
     *
     * @param roleCreationDto payload de criação de {@link Role}.
     * @return {@link RoleDto}.
     */
    @PostMapping(path = "role.create", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates a new Role.")
    @ApiResponses({
        @ApiResponse(code = 201, message = CREATED, response = RoleDto.class),
        @ApiResponse(code = 400, message = BAD_REQUEST, response = ResponseErrorDto.class),
        @ApiResponse(code = 415, message = UNSUPPORTED_MEDIA_TYPE, response = ResponseErrorDto.class),
        @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR, response = ResponseErrorDto.class)
    })
    public RoleDto createRole(@ApiParam(name = "role", required = true) @Valid @RequestBody RoleCreationDto roleCreationDto) {
        log.info("Request to create a new role received: {}", kv("roleCreationDto", roleCreationDto));

        final Role role = roleService.createRole(roleMapper.roleCreationDtoToRole(roleCreationDto));

        log.info("Role {} created", kv(ROLE_ID, role.getRoleId()));

        return roleMapper.roleToRoleDto(role);
    }

    /**
     * Returns the searched {@link Role}.
     *
     * @param roleId id da {@link Role}.
     * @return {@link RoleDto}.
     */
    @GetMapping(path = "role/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Returns the searched Role.")
    @ApiResponses({
        @ApiResponse(code = 200, message = OK_STATUS, response = RoleDto.class),
        @ApiResponse(code = 400, message = BAD_REQUEST, response = ResponseErrorDto.class),
        @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR, response = ResponseErrorDto.class)
    })
    public RoleDto searchRole(@ApiParam(name = ROLE_ID, required = true) @PathVariable String roleId) {
        log.info("Request received to retrieve Role: {}", kv(ROLE_ID, roleId));

        final RoleDto roleDto = roleMapper.roleToRoleDto(roleService.searchRole(roleId));

        log.info("Retrieved Role: {}, {}", kv(ROLE_ID, roleId), kv("name", roleDto.getName()));

        return roleDto;
    }

}
