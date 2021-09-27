package com.marvin.roleservice.domain;

import com.marvin.roleservice.validation.annotation.Uuid;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Role domain object.
 */
@Data
@Document(collection = "roles")
@TypeAlias("ROLE")
public class Role {

    @Id
    @Uuid
    private String roleId = UUID.randomUUID().toString();

    private String name;

    private String description;

    private Set<Member> members = new HashSet<>();

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

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
