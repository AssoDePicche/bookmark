package com.bookmark.book_list_service.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookListRepository extends JpaRepository<JpaBookListEntity, UUID> {
  Page<JpaBookListEntity> findAll(Pageable pageable);

  Optional<JpaBookListEntity> findById(UUID id);

  Optional<JpaBookListEntity> findByTitle(String title);

  Page<JpaBookListEntity> findByUser(UUID user, Pageable pageable);
}
