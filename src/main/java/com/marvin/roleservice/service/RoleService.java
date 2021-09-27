package com.marvin.roleservice.service;

import com.marvin.roleservice.domain.Member;
import com.marvin.roleservice.domain.Role;

/**
 * Service for {@link Role} operations.
 */
public interface RoleService {

    /**
     * Creates a new {@link Role}.
     *
     * @param role {@link Role} to be created.
     * @return {@link Role} created.
     */
    Role createRole(Role role);

    /**
     * Searches a {@link Role} by its identifier.
     *
     * @param roleId role identifier.
     * @return {@link Role} retrieved.
     */
    Role searchRole(String roleId);

    /**
     * Inserts a {@link Member} to the {@link Role}.
     */
    void insertMember(String roleId, Member member);

}
