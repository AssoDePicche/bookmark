package com.bookmark.iam.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIdentityRepository extends JpaRepository<JpaIdentityEntity, UUID> {
  Optional<JpaIdentityEntity> findByUsername(String username);
}
