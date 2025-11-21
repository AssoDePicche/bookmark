package com.bookmark.user_service.infrastructure.persistence;

import com.bookmark.user_service.domain.User;
import com.bookmark.user_service.domain.UserId;
import com.bookmark.user_service.domain.UserRole;
import java.util.Set;
import java.util.stream.Collectors;

public class JpaUserEntityMapper {
  public static User map(JpaUserEntity entity) {
    return new User(new UserId(entity.getId()), entity.getEmail(), entity.getRoles());
  }

  public static JpaUserEntity map(User user) {
    return new JpaUserEntity(user.getId().value(), user.getEmail().toString(),
        user.getRoles().stream().map(UserRole::toString).collect(Collectors.toSet()));
  }
}
