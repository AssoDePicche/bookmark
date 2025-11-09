package com.bookmark.catalog.infrastructure.persistence;

import com.bookmark.catalog.domain.Book;
import com.bookmark.catalog.domain.BookRepository;
import com.bookmark.catalog.domain.Title;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class JpaBookRepositoryAdapter implements BookRepository {
  private final JpaBookRepository repository;

  public JpaBookRepositoryAdapter(JpaBookRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<Book> findByTitle(Title title) {
    return repository.findByTitle(title.toString()).map(JpaBookEntityMapper::map);
  }

  @Override
  public Book save(Book book) {
    JpaBookEntity entity = JpaBookEntityMapper.map(book);

    return JpaBookEntityMapper.map(repository.save(entity));
  }
}
