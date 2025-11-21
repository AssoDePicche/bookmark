package com.bookmark.catalog_service.application;

import com.bookmark.catalog_service.domain.Book;
import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;

public interface CatalogService {
  Paged<Book> query(Pagination pagination);

  Book save(Book book);
}
