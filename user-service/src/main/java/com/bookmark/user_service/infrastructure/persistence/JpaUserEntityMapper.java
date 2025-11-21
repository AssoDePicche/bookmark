package com.bookmark.user.infrastructure.persistence;

import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserId;
import com.bookmark.user.domain.UserRole;
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
