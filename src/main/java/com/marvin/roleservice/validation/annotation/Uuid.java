package com.marvin.roleservice.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The {@link java.lang.annotation.ElementType#FIELD} or {@link java.lang.annotation.ElementType#PARAMETER} annotated must be a valid UUID.
 */
@Target({FIELD, PARAMETER})
@NotNull
@Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
@ReportAsSingleViolation
public @interface Uuid {

    /**
     * Defines the message in case of failure.
     * Default message is "Invalid UUID"
     *
     * @return message
     */
    String message() default "Invalid UUID";

    /**
     * Defines the groups for validation.
     *
     * @return groups
     */
    Class[] groups() default {};

    /**
     * Define the payload for validation.
     *
     * @return payload
     */
    Class<? extends Payload>[] payload() default {};

}

