package com.bookmark.interfaces.dto.user;

import jakarta.validation.constraints.NotEmpty;

public record UserAuthenticationRequest(
    @NotEmpty(message = "You must inform an username") String username,
    @NotEmpty(message = "You must inform a password") String password) {}
