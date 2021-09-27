package com.marvin.roleservice.mapping

import com.marvin.roleservice.domain.Role
import com.marvin.roleservice.rest.dto.RoleCreationDto
import com.marvin.roleservice.rest.dto.RoleDto
import org.mapstruct.factory.Mappers
import spock.lang.Specification
import spock.lang.Unroll

class RoleMapperImplSpec extends Specification {
    RoleMapper roleMapper = Mappers.getMapper(RoleMapper)

    @Unroll
    def 'RoleCreationDto must be mapped to Role'() {
        given: 'a RoleCreationDto'
        RoleCreationDto roleCreationDto = new RoleCreationDto()
        roleCreationDto.name = name
        roleCreationDto.description = description

        when: 'Role is created'
        Role role = roleMapper.roleCreationDtoToRole(roleCreationDto)

        then: 'Role must be mapped'
        role.roleId != null
        role.name == name
        role.description == description

        where:
        name     | description
        'name'   | 'description'
        'name'   | 'description'
        'name'   | 'description'
    }

    def 'Role must be mapped to RoleDto'() {
        given: 'a Role'
        Role role = new Role()
        role.roleId = roleId
        role.name = name
        role.description = description

        when: 'RoleDto is created'
        RoleDto roleDto = roleMapper.roleToRoleDto(role)

        then: 'RoleDto must be mapped'
        roleDto.roleId == roleId
        roleDto.name == name
        roleDto.description == description

        where:
        roleId     | name      | description
        'roleId'   | 'name'    | 'description'
    }

    def 'RoleCreationDto null must be mapped to a Role null'() {
        given: 'a RoleCreationDto null'

        when: 'Role is created'
        Role role = roleMapper.roleCreationDtoToRole(null)

        then: 'Role must be null'
        role == null
    }

    def 'Role null must be mapped to RoleDto null'() {
        given: 'um Role null'

        when: 'RoleDto is created'
        RoleDto roleDto = roleMapper.roleToRoleDto(null)

        then: 'RoleDto must be null'
        roleDto == null
    }
}
