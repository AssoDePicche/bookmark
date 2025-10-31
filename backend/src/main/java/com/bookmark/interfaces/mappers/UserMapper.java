package com.bookmark.interfaces.mappers;

import com.bookmark.domain.User;
import com.bookmark.domain.UserId;
import com.bookmark.infrastructure.persistence.JpaUserEntity;
import com.bookmark.interfaces.dto.user.UserAuthenticationRequest;
import com.bookmark.interfaces.dto.user.UserRegistrationRequest;
import com.bookmark.interfaces.dto.user.UserResponse;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  private final DateTimeFormatter formatter;

  public UserMapper(DateTimeFormatter formatter) {
    this.formatter = formatter;
  }

  public User map(JpaUserEntity user) {
    return new User(new UserId(user.getId()), user.getEmail(), user.getUsername(),
        user.getPassword(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
  }

  public JpaUserEntity mapToJpa(User user) {
    return new JpaUserEntity(user.getEmail().toString(), user.getUsername().toString(),
        user.getPassword().toString(), user.getRole().toString());
  }

  public UserResponse map(User user) {
    return new UserResponse(user.getUsername().toString(), user.getEmail().toString(),
        user.getCreatedAt().format(formatter), user.getUpdatedAt().format(formatter));
  }

  public User map(UserRegistrationRequest request) {
    return new User(request.email(), request.username(), request.password(), request.role());
  }
}
