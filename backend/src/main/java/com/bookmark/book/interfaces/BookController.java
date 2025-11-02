package com.bookmark.book.interfaces;

import com.bookmark.book.application.BookService;
import com.bookmark.book.domain.Book;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/books")
@RestController
@Validated
public class BookController {
  private final BookService service;

  public BookController(BookService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<BookResponse> post(@RequestBody @Valid BookRequest request)
      throws URISyntaxException {
    Book book = BookMapper.map(request);

    service.save(book);

    String pathname = "/api/books/" + book.getId();

    URI uri = new URI(pathname);

    BookResponse response = BookMapper.map(book);

    return ResponseEntity.created(uri).body(response);
  }
}
