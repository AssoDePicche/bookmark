package com.bookmark.iam.interfaces;

import jakarta.validation.constraints.NotEmpty;

public record AuthenticationRequest(
    @NotEmpty(message = "You must inform an username") String username,
    @NotEmpty(message = "You must inform a password") String password) {}
