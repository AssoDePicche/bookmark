package com.bookmark.booklist.domain;

import com.bookmark.booklist.domain.BookListId;
import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;

public interface BookListEntryRepository {
  Paged<BookListEntry> findByBookList(BookListId id, Pagination pagination);
}
