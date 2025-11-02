package com.bookmark.user.interfaces;

import com.bookmark.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public static UserResponse map(User user) {
    return new UserResponse(user.getUsername().toString(), user.getEmail().toString());
  }

  public static User map(UserRegistrationRequest request) {
    return new User(request.email(), request.username(), request.password(), request.role());
  }
}
