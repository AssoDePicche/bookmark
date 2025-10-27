package com.bookmark.interfaces.dto.user;

public record UserResponse(
    Long id, String username, String email, String createdAt, String updatedAt) {}
