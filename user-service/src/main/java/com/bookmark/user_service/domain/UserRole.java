package com.bookmark.user.domain;

public enum UserRole {
  ADMIN,
  USER;

  public static UserRole from(String role) {
    return switch (role.toLowerCase()) {
      case "admin" -> ADMIN;
      case "user" -> USER;
      default -> throw new IllegalArgumentException(String.format("Invalid User Role '%s'", role));
    };
  }
}
