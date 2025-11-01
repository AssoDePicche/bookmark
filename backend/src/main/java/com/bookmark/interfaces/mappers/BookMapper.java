package com.bookmark.interfaces.mappers;

import com.bookmark.domain.Book;
import com.bookmark.interfaces.dto.book.BookRequest;
import com.bookmark.interfaces.dto.book.BookResponse;
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
