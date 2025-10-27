package com.bookmark.infrastructure.persistence;

import com.bookmark.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail_Value(String email);

  Optional<User> findByUsername_Value(String username);
}
