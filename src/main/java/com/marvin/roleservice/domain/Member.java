package com.marvin.roleservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Member domain object.
 */
@Data
@Document(collection = "members")
@TypeAlias("MEMBER")
public class Member {

    @Id
    private String userId;

    @Id
    private String teamId;

    private String roleId;
}
