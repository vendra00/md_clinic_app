package com.gv.md_clinic_app.utils;

import com.gv.md_clinic_app.model.enums.UserRole;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Utility class for security-related operations.
 */
@Component
public class SecurityUtils {

    /**
     * Returns the role of the currently authenticated user.
     * @return the role of the currently authenticated user.
     */
    public static UserRole getCurrentUserRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(String::toUpperCase) // Assuming roles are stored as uppercase strings
                .map(UserRole::valueOf)   // Convert string to UserRole enum
                .findFirst()
                .orElse(UserRole.PATIENT); // Default role if none found
    }

    /**
     * Returns the ID of the currently authenticated user.
     * @return the ID of the currently authenticated user.
     */
    public static @NotNull Long getCurrentAuthenticatedPersonId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("No authenticated user found");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return Long.parseLong(username);  // Assuming the username is the patient's ID
        } else {
            throw new AccessDeniedException("Unrecognized principal type");
        }
    }
}

