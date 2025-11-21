package com.bookmark.catalog.infrastructure.persistence;

import com.bookmark.catalog.domain.Book;
import com.bookmark.catalog.domain.BookRepository;
import com.bookmark.catalog.domain.Title;
import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;
import com.bookmark.common.infrastructure.mapper.PageMapper;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class JpaBookRepositoryAdapter implements BookRepository {
  private final JpaBookRepository repository;

  public JpaBookRepositoryAdapter(JpaBookRepository repository) {
    this.repository = repository;
  }

  @Override
  public Paged<Book> findAll(Pagination pagination) {
    Pageable pageable = PageMapper.map(pagination);

    Page<JpaBookEntity> page = repository.findAll(pageable);

    return PageMapper.map(page, (book) -> JpaBookEntityMapper.map(book));
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
