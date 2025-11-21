package com.bookmark.user_service.interfaces;

import java.util.Set;

public record UserResponse(String id, String email, Set<String> roles) {}
