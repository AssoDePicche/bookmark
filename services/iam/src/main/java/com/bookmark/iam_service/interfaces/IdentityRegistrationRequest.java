package com.bookmark.iam_service.interfaces;

import java.util.Set;

public record IdentityRegistrationRequest(String username, String password, Set<String> roles) {}
