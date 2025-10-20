package com.bookmark.infrastructure.persistence;

import com.bookmark.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long> {
  Page<Book> findAll(Pageable pageable);
}
