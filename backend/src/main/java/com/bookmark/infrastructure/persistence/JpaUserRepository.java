package com.bookmark.infrastructure.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<JpaUser, Long> {
  Optional<JpaUser> findByEmail(String email);

  Optional<JpaUser> findByUsername(String username);
}
