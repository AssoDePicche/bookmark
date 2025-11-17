package com.bookmark.user.interfaces;

import java.util.Set;

public record UserResponse(String id, String email, Set<String> roles) {}
