package com.bookmark.book_list_service.infrastructure.persistence;

import com.bookmark.book_list_service.domain.BookListEntry;
import com.bookmark.book_list_service.domain.BookListEntryId;
import com.bookmark.book_list_service.domain.BookListEntryRepository;
import com.bookmark.book_list_service.domain.BookListId;
import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.domain.Pagination;
import com.bookmark.book_list_service.infrastructure.mapper.PageMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class JpaBookListEntryRepositoryAdapter implements BookListEntryRepository {
  private final JpaBookListEntryRepository repository;

  public JpaBookListEntryRepositoryAdapter(JpaBookListEntryRepository repository) {
    this.repository = repository;
  }

  @Override
  public BookListEntryId nextIdentity() {
    return new BookListEntryId();
  }

  // TODO: fix entity mapping in line 34
  @Override
  public Paged<BookListEntry> findByBookList(BookListId id, Pagination pagination) {
    Pageable pageable = PageMapper.map(pagination);

    Page<JpaBookListEntryEntity> page = repository.findAll(pageable);

    return PageMapper.map(page, (entry) -> JpaBookListEntryEntityMapper.map(entry, null));
  }
}
