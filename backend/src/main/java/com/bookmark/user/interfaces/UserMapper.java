package com.bookmark.user.interfaces;

import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserRole;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public static UserResponse map(User user) {
    return new UserResponse(user.getId().toString(), user.getEmail().toString(),
        user.getRoles().stream().map(UserRole::toString).collect(Collectors.toSet()));
  }
}
