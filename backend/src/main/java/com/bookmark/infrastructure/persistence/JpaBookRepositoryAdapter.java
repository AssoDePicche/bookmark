package com.bookmark.infrastructure.persistence;

import com.bookmark.domain.Book;
import com.bookmark.domain.BookRepository;
import com.bookmark.domain.Title;
import com.bookmark.interfaces.mappers.BookMapper;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class JpaBookRepositoryAdapter implements BookRepository {
  private final BookMapper mapper;

  private final JpaBookRepository repository;

  public JpaBookRepositoryAdapter(BookMapper mapper, JpaBookRepository repository) {
    this.mapper = mapper;

    this.repository = repository;
  }

  @Override
  public Optional<Book> findByTitle(Title title) {
    return repository.findByTitle(title.toString()).map(mapper::map);
  }

  @Override
  public Book save(Book book) {
    JpaBookEntity entity = mapper.mapToJpa(book);

    return mapper.map(repository.save(entity));
  }
}
