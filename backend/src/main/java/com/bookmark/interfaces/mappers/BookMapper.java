package com.bookmark.interfaces.mappers;

import com.bookmark.domain.Book;
import com.bookmark.domain.BookId;
import com.bookmark.infrastructure.persistence.JpaBookEntity;
import com.bookmark.interfaces.dto.book.BookRequest;
import com.bookmark.interfaces.dto.book.BookResponse;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
  public Book map(JpaBookEntity book) {
    return new Book(new BookId(book.getId()), book.getIsbn(), book.getTitle(),
        book.getDescription(), book.getGenre(), book.getPublicationDate(), book.getCreatedAt(),
        book.getUpdatedAt());
  };

  public JpaBookEntity mapToJpa(Book book) {
    return new JpaBookEntity(book.getIsbn().toString(), book.getTitle().toString(),
        book.getDescription().toString(), book.getGenre(), book.getPublicationDate());
  }

  public Book map(BookRequest request) {
    return new Book(request.isbn(), request.title(), request.description(), request.genre(),
        request.publicationDate());
  }

  public BookResponse map(Book book) {
    return new BookResponse(book.getIsbn().toString(), book.getTitle().toString(),
        book.getDescription().toString(), book.getGenre(),
        book.getPublicationDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
  }
}
