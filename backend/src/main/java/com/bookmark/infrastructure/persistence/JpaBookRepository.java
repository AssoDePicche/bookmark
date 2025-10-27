package com.bookmark.infrastructure.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookRepository extends JpaRepository<JpaBook, Long> {
  Optional<JpaBook> findByTitle(String title);
}
