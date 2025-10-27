package com.bookmark.interfaces.mappers;

import com.bookmark.domain.User;
import com.bookmark.interfaces.dto.user.UserAuthenticationRequest;
import com.bookmark.interfaces.dto.user.UserRegistrationRequest;
import com.bookmark.interfaces.dto.user.UserResponse;
import java.time.format.DateTimeFormatter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  private final DateTimeFormatter formatter;

  public UserMapper(DateTimeFormatter formatter) {
    this.formatter = formatter;
  }

  public UserResponse map(User user) {
    return new UserResponse(user.getId(), user.getUsername().toString(), user.getEmail().toString(),
        user.getCreatedAt().format(formatter), user.getUpdatedAt().format(formatter));
  }

  public User map(UserDetails details) {
    return new User(details.getUsername(), details.getPassword());
  }

  public User map(UserRegistrationRequest request) {
    return new User(request.email(), request.username(), request.password());
  }

  public User map(UserAuthenticationRequest request) {
    return new User(request.username(), request.password());
  }
}
