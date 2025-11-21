package com.bookmark.catalog_service.interfaces;

import com.bookmark.catalog_service.domain.Book;
import com.bookmark.catalog_service.domain.Paged;
import com.bookmark.catalog_service.infrastructure.mapper.PageMapper;
import java.time.format.DateTimeFormatter;

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

  public static Paged<BookResponse> map(Paged<Book> pagedBooks) {
    return PageMapper.map(pagedBooks, (book) -> map(book));
  }
}
