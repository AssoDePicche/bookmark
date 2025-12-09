package com.bookmark.catalog_service.domain;

import java.util.Optional;

public interface BookRepository {
  Paged<Book> findAll(Pagination pagination);

  Optional<Book> findByTitle(Title title);

  Book save(Book book);
}
