package com.bookmark.user.infrastructure.persistence;

import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserId;

public class JpaUserEntityMapper {
  public static User map(JpaUserEntity entity) {
    return new User(new UserId(entity.getId()), entity.getEmail(), entity.getUsername(),
        entity.getPassword(), entity.getRole());
  }

  public static JpaUserEntity map(User user) {
    return new JpaUserEntity(user.getId().value(), user.getEmail().toString(),
        user.getUsername().toString(), user.getPassword().toString(), user.getRole().toString());
  }
}
