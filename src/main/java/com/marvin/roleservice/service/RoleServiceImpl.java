package com.marvin.roleservice.service;

import com.marvin.roleservice.domain.Member;
import com.marvin.roleservice.domain.Role;
import com.marvin.roleservice.exception.AlreadyMemberException;
import com.marvin.roleservice.exception.NotFoundException;
import com.marvin.roleservice.repository.RoleRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Set;

/**
 * Service Implementation for {@link Role} operations.
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private static final String ROLE_NOT_FOUND = "Role '%s' was not found.";

    private final RoleRepository roleRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Role createRole(Role role) {
        return roleRepository.insert(role);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role searchRole(String roleId) {
        return roleRepository.findById(Objects.requireNonNull(roleId)).orElseThrow(() -> {
            throw new NotFoundException(String.format(ROLE_NOT_FOUND, roleId));
        });
    }

    @Override
    public void insertMember(String roleId, Member member) {
        final Role role = searchRole(roleId);
        insertMember(role, member);
        roleRepository.save(role);
    }

    private void insertMember(Role role, Member member) {
        final Set<Member> members = role.getMembers();
        if (members.contains(member)) {
            throw new AlreadyMemberException(String.format("User '%s' is already a member of '%s'", member.getUserId(), role.getName()));
        }
        members.add(member);
    }
}
