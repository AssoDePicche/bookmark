package com.bookmark.iam.interfaces;

import java.util.Set;
import java.util.UUID;

public record IdentityResponse(UUID id, String username, Set<String> roles) {}
