package com.bookmark.user_service.interfaces;

import com.bookmark.user_service.domain.User;
import com.bookmark.user_service.domain.UserRole;
import java.util.stream.Collectors;

public class UserMapper {
  public static UserResponse map(User user) {
    return new UserResponse(user.getId().toString(), user.getEmail().toString(),
        user.getRoles().stream().map(UserRole::toString).collect(Collectors.toSet()));
  }
}
