package com.bookmark.catalog_service.infrastructure.persistence;

import com.bookmark.catalog_service.domain.Book;
import com.bookmark.catalog_service.domain.BookRepository;
import com.bookmark.catalog_service.domain.Paged;
import com.bookmark.catalog_service.domain.Pagination;
import com.bookmark.catalog_service.domain.Title;
import com.bookmark.catalog_service.infrastructure.mapper.PageMapper;
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
