package com.marvin.roleservice.mapping;

import org.mapstruct.Mapper;

import com.marvin.roleservice.domain.Role;
import com.marvin.roleservice.rest.dto.RoleCreationDto;
import com.marvin.roleservice.rest.dto.RoleDto;

/**
 * Mapper for {@link Role}.
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {

    /**
     * Map values from {@link RoleCreationDto} to {@link Role}.
     *
     * @param roleCreationDto {@link RoleCreationDto}
     * @return {@link Role}
     */
    Role roleCreationDtoToRole(RoleCreationDto roleCreationDto);

    /**
     * Map values from {@link Role} to {@link RoleDto}.
     *
     * @param role {@link Role}
     * @return {@link RoleDto}
     */
    RoleDto roleToRoleDto(Role role);

}


