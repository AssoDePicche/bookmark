package com.bookmark.book.interfaces;

import com.bookmark.book.domain.Book;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
  public static Book map(BookRequest request) {
    return new Book(request.isbn(), request.title(), request.description(), request.genre(),
        request.publicationDate());
  }

  public static BookResponse map(Book book) {
    return new BookResponse(book.getIsbn().toString(), book.getTitle().toString(),
        book.getDescription().toString(), book.getGenre(),
        book.getPublicationDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
  }
}
