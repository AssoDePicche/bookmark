package com.bookmark.review.infrastructure.persistence;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReviewRepository extends JpaRepository<JpaReviewEntity, UUID> {
  List<JpaReviewEntity> findByBook(UUID book);

  Page<JpaReviewEntity> findByBook(UUID book, Pageable pageable);

  Page<JpaReviewEntity> findByUser(UUID user, Pageable pageable);
}
