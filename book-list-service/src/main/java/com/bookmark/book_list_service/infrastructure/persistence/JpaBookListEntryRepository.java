package com.bookmark.book_list_service.infrastructure.persistence;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookListEntryRepository extends JpaRepository<JpaBookListEntryEntity, UUID> {
  Page<JpaBookListEntryEntity> findByBookList(UUID id, Pageable pageable);
}
