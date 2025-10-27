package com.bookmark.infrastructure.persistence;

import com.bookmark.domain.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long> {
  Optional<Book> findByTitle_Value(String title);
}
