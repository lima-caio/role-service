package com.marvin.roleservice.service

import com.marvin.roleservice.domain.Member
import com.marvin.roleservice.domain.Role
import com.marvin.roleservice.exception.AlreadyMemberException
import com.marvin.roleservice.exception.NotFoundException
import com.marvin.roleservice.repository.RoleRepository
import spock.lang.Specification

import java.util.concurrent.TimeUnit
import java.util.List

class RoleServiceImplSpec extends Specification {

    RoleRepository roleRepository = Mock(RoleRepository)

    RoleService roleService = new RoleServiceImpl(roleRepository)

    def 'Role must be created without errors'() {
        given: 'a Role'
        Role roleMock = Mock(Role)

        1 * roleRepository.insert(roleMock) >> roleMock

        when: 'Role is created'
        Role role = roleService.createRole(roleMock)

        then: 'role must be returned'
        role == roleMock

        and: 'no exception thrown'
        noExceptionThrown()
    }

    def 'Role must be found without errors'() {
        given: 'a roleId'
        String roleId = 'roleId'
        Role roleMock = Mock(Role)

        1 * roleRepository.findById(roleId) >> Optional.of(roleMock)

        when: 'Role searched'
        Role role = roleService.searchRole(roleId)

        then: 'Role must be returned'
        role == roleMock

        and: 'no exception thrown'
        noExceptionThrown()
    }

    def 'NotFoundException must be thrown when a Role is not found'() {
        given: 'a roleId'
        String roleId = 'roleId'

        1 * roleRepository.findById(roleId) >> Optional.empty()

        when: 'Role is searched'
        roleService.searchRole(roleId)

        then: 'NotFoundException is thrown'
        thrown(NotFoundException)
    }

    def 'Member must be added to Role without errors'() {
        given: 'a roleId'
        String roleId = 'roleId'
        Role role = new Role()
        Member member = new Member()
        member.userId = 'userId'
        member.teamId = 'teamId'

        1 * roleRepository.findById(roleId) >> Optional.of(role)
        1 * roleRepository.save(role) >> role

        when: 'Member is inserted to Role'
        roleService.insertMember(roleId, member)

        then: 'Member must be part of Role'
        role.members.contains(member)
    }

    def 'AlreadyMemberException must be thrown when inserting a member already cointained in Role'() {
        given: 'a roleId'
        String roleId = 'roleId'
        Role role = new Role()
        Member alreadyMember = new Member()
        alreadyMember.userId = 'userId'
        alreadyMember.teamId = 'teamId'
        role.members = [alreadyMember]
        Member member = new Member()
        member.userId = 'userId'
        member.teamId = 'teamId'

        1 * roleRepository.findById(roleId) >> Optional.of(role)
        0 * roleRepository.save(role)

        when: 'Member is inserted to Role'
        roleService.insertMember(roleId, member)

        then: 'AlreadyMemberException is thrown'
        thrown(AlreadyMemberException)
    }

}
