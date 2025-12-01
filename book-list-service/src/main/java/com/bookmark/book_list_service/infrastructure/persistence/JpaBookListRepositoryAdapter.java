package com.bookmark.book_list_service.infrastructure.persistence;

import com.bookmark.book_list_service.domain.BookList;
import com.bookmark.book_list_service.domain.BookListId;
import com.bookmark.book_list_service.domain.BookListRepository;
import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.domain.Pagination;
import com.bookmark.book_list_service.domain.Title;
import com.bookmark.book_list_service.domain.UserId;
import com.bookmark.book_list_service.infrastructure.mapper.PageMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class JpaBookListRepositoryAdapter implements BookListRepository {
  private final JpaBookListRepository repository;

  public JpaBookListRepositoryAdapter(JpaBookListRepository repository) {
    this.repository = repository;
  }

  @Override
  public Paged<BookList> findAll(Pagination pagination) {
    Pageable pageable = PageMapper.map(pagination);

    Page<JpaBookListEntity> page = repository.findAll(pageable);

    return PageMapper.map(page, JpaBookListEntityMapper::map);
  }

  @Override
  public Optional<BookList> findById(BookListId id) {
    UUID bookListId = id.value();

    return repository.findById(bookListId).map(JpaBookListEntityMapper::map);
  }

  @Override
  public Optional<BookList> findByTitle(Title title) {
    var bookListTitle = title.toString();

    return repository.findByTitle(bookListTitle).map(JpaBookListEntityMapper::map);
  }

  @Override
  public Paged<BookList> findByUser(UserId user, Pagination pagination) {
    Pageable pageable = PageMapper.map(pagination);

    UUID userId = user.value();

    Page<JpaBookListEntity> page = repository.findByUser(userId, pageable);

    return PageMapper.map(page, JpaBookListEntityMapper::map);
  }

  @Override
  public BookListId nextIdentity() {
    return new BookListId();
  }

  @Override
  public BookList save(BookList bookList) {
    JpaBookListEntity entity = JpaBookListEntityMapper.map(bookList);

    repository.save(entity);

    return JpaBookListEntityMapper.map(entity);
  }
}
