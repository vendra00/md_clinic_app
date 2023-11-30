package com.gv.md_clinic_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Main class of the application.
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class })
public class MdClinicAppApplication {

    /**
     * Main method of the application.
     * @param args Arguments of the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(MdClinicAppApplication.class, args);
    }

}
