package com.bookmark.catalog_service.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookRepository extends JpaRepository<JpaBookEntity, UUID> {
  Page<JpaBookEntity> findAll(Pageable pageable);

  Optional<JpaBookEntity> findByTitle(String title);
}
