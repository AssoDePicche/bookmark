package com.bookmark.booklist.infrastructure.persistence;

import com.bookmark.booklist.domain.BookListEntry;
import com.bookmark.booklist.domain.BookListEntryRepository;
import com.bookmark.booklist.domain.BookListId;
import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;
import com.bookmark.common.infrastructure.mapper.PageMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class JpaBookListEntryRepositoryAdapter implements BookListEntryRepository {
  private final JpaBookListEntryRepository repository;

  public JpaBookListEntryRepositoryAdapter(JpaBookListEntryRepository repository) {
    this.repository = repository;
  }

  // TODO: fix entity mapping in line 28
  @Override
  public Paged<BookListEntry> findByBookList(BookListId id, Pagination pagination) {
    Pageable pageable = PageMapper.map(pagination);

    Page<JpaBookListEntryEntity> page = repository.findAll(pageable);

    return PageMapper.map(page, (entry) -> JpaBookListEntryEntityMapper.map(entry, null));
  }
}
