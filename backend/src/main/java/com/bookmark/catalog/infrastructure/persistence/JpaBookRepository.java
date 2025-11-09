package com.bookmark.catalog.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookRepository extends JpaRepository<JpaBookEntity, UUID> {
  Optional<JpaBookEntity> findByTitle(String title);
}
