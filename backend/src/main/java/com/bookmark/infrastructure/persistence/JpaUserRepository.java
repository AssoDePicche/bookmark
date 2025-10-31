package com.bookmark.infrastructure.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<JpaUserEntity, Long> {
  Optional<JpaUserEntity> findByEmail(String email);

  Optional<JpaUserEntity> findByUsername(String username);
}
