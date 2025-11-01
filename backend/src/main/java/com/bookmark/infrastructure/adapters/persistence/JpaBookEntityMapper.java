package com.bookmark.infrastructure.adapters.persistence;

import com.bookmark.domain.Book;
import com.bookmark.domain.BookId;

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
