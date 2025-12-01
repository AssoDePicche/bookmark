package com.bookmark.book_list_service.domain;

import com.bookmark.book_list_service.domain.BookListId;
import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.domain.Pagination;

public interface BookListEntryRepository {
  Paged<BookListEntry> findByBookList(BookListId id, Pagination pagination);

  BookListEntryId nextIdentity();

  BookListEntry save(BookListEntry entry);
}
