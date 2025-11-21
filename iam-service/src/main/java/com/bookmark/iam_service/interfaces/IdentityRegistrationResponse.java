package com.bookmark.iam_service.interfaces;

import java.util.Set;
import java.util.UUID;

public record IdentityRegistrationResponse(UUID id, String username, Set<String> roles) {}
