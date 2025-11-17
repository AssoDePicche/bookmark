package com.bookmark.iam.interfaces;

import java.util.Set;

public record IdentityRegistrationRequest(String username, String password, Set<String> roles) {}
