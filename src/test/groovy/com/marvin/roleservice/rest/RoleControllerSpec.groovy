package com.marvin.roleservice.rest

import com.marvin.roleservice.domain.Role

import com.marvin.roleservice.mapping.RoleMapper
import com.marvin.roleservice.rest.dto.RoleCreationDto
import com.marvin.roleservice.rest.dto.RoleDto

import com.marvin.roleservice.service.RoleService
import spock.lang.Specification

class RoleControllerSpec extends Specification {

    RoleMapper roleMapper = Mock(RoleMapper)
    RoleService roleService = Mock(RoleService)

    RoleController roleController = new RoleController(roleService, roleMapper)

    def 'Role must be created without errors when receiving a RoleCreationDto'() {
        given: 'a RoleCreationDto'
        RoleCreationDto roleCreationDto = Mock(RoleCreationDto)
        Role role = Mock(Role)
        RoleDto roleDtoMock = Mock(RoleDto)

        1 * roleMapper.roleCreationDtoToRole(roleCreationDto) >> role
        1 * roleService.createRole(role) >> role
        1 * roleMapper.roleToRoleDto(role) >> roleDtoMock

        when: 'Role is created'
        RoleDto roleDto = roleController.createRole(roleCreationDto)

        then: 'RoleDto must be returned'
        roleDto == roleDtoMock

        and: 'no exception thrown'
        noExceptionThrown()
    }

    def 'Role must be retrieved without errors when receiving a valid roleId'() {
        given: 'a roleId'
        String roleId = 'roleId'
        Role role = Mock(Role)
        RoleDto roleDtoMock = Mock(RoleDto)

        1 * roleService.searchRole(roleId) >> role
        1 * roleMapper.roleToRoleDto(role) >> roleDtoMock

        when: 'Role is searched'
        RoleDto roleDto = roleController.searchRole(roleId)

        then: 'RoleDto must be returned'
        roleDto == roleDtoMock

        and: 'no exception thrown'
        noExceptionThrown()
    }

}
