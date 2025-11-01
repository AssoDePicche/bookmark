package com.bookmark.domain;

import java.util.Optional;

public interface UserRepository {
  Optional<User> findByEmail(Email email);

  Optional<User> findById(UserId id);

  Optional<User> findByUsername(Username username);

  User save(User user);
}
