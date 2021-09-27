package com.marvin.roleservice.exception;

/**
 * Exception for when an user is already a member of a role.
 */
public class AlreadyMemberException extends RuntimeException {

    public AlreadyMemberException(String message) {
        super(message);
    }
}
