package com.bookmark.catalog.application;

import com.bookmark.catalog.domain.Book;
import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;

public interface BookService {
  Paged<Book> query(Pagination pagination);

  Book save(Book book);
}
