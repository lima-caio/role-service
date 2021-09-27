package com.marvin.roleservice.service;

import com.marvin.roleservice.domain.Role;
import com.marvin.roleservice.exception.NotFoundException;
import com.marvin.roleservice.repository.RoleRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

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
}
