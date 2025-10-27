package com.bookmark.interfaces.mappers;

import com.bookmark.domain.Book;
import com.bookmark.infrastructure.persistence.JpaBook;
import com.bookmark.interfaces.dto.book.BookRequest;
import com.bookmark.interfaces.dto.book.BookResponse;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
  public Book map(JpaBook book) {
    return new Book(book.getId(), book.getISBN(), book.getTitle(), book.getDescription(),
        book.getGenre(), book.getPublicationDate(), book.getCreatedAt(), book.getUpdatedAt());
  };

  public JpaBook mapToJpa(Book book) {
    return new JpaBook(book.getISBN().toString(), book.getTitle().toString(),
        book.getDescription().toString(), book.getGenre(), book.getPublicationDate());
  }

  public Book map(BookRequest request) {
    return new Book(request.isbn(), request.title(), request.description(), request.genre(),
        request.publicationDate());
  }

  public BookResponse map(Book book) {
    return new BookResponse(book.getISBN().toString(), book.getTitle().toString(),
        book.getDescription().toString(), book.getGenre(),
        book.getPublicationDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
  }
}
