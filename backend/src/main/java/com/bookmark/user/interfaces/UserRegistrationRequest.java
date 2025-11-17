package com.bookmark.user.interfaces;

import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public record UserRegistrationRequest(@NotEmpty(message = "You must inform an email") String email,
    @NotEmpty(message = "You must inform an username") String username,
    @NotEmpty(message = "You must inform a password") String password,
    @NotEmpty(message = "You must inform at least a role") Set<String> roles) {}
