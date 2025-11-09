package com.bookmark.catalog.domain;

import java.util.Optional;

public interface BookRepository {
  Optional<Book> findByTitle(Title title);

  Book save(Book book);
}
