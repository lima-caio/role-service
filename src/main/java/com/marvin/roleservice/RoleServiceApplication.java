package com.marvin.roleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starting point of the service.
 */
@SpringBootApplication
@SuppressWarnings("PMD.UseUtilityClass")
public class RoleServiceApplication {

    /**
     * Starting point of springBoot service.
     *
     * @param args string arguments
     */
    public static void main(final String... args) {
        SpringApplication.run(RoleServiceApplication.class, args);
    }
}
