package com.bookmark.interfaces.mappers;

import com.bookmark.domain.Book;
import com.bookmark.interfaces.dto.book.BookRequest;
import com.bookmark.interfaces.dto.book.BookResponse;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
  public Book map(BookRequest request) {
    return new Book(
        request.title(), request.description(), request.genre(), request.publicationDate());
  }

  public BookResponse map(Book book) {
    return new BookResponse(book.getTitle().toString(), book.getDescription().toString(),
        book.getGenre(), book.getPublicationDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
  }
}
