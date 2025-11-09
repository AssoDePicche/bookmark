package com.bookmark.catalog.domain;

import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;
import java.util.Optional;

public interface BookRepository {
  Paged<Book> findAll(Pagination pagination);

  Optional<Book> findByTitle(Title title);

  Book save(Book book);
}
