package com.bookmark.interfaces.mappers;

import com.bookmark.domain.User;
import com.bookmark.interfaces.dto.user.UserAuthenticationRequest;
import com.bookmark.interfaces.dto.user.UserRegistrationRequest;
import com.bookmark.interfaces.dto.user.UserResponse;
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
