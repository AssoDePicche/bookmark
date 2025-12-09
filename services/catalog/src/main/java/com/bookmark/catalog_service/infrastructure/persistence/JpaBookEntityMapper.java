package com.bookmark.catalog_service.infrastructure.persistence;

import com.bookmark.catalog_service.domain.Book;
import com.bookmark.catalog_service.domain.BookId;

public class JpaBookEntityMapper {
  public static Book map(JpaBookEntity book) {
    return new Book(new BookId(book.getId()), book.getIsbn(), book.getTitle(),
        book.getDescription(), book.getGenre(), book.getPublicationDate());
  };

  public static JpaBookEntity map(Book book) {
    return new JpaBookEntity(book.getId().value(), book.getIsbn().toString(),
        book.getTitle().toString(), book.getDescription().toString(), book.getGenre(),
        book.getPublicationDate());
  }
}
