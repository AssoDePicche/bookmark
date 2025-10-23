package com.bookmark.interfaces.controllers;

import com.bookmark.application.BookService;
import com.bookmark.domain.Book;
import com.bookmark.interfaces.dto.request.BookRequest;
import com.bookmark.interfaces.dto.response.BookResponse;
import com.bookmark.interfaces.mappers.BookMapper;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/books")
@RestController
public class BookController {
  private final BookMapper mapper;

  private final BookService service;

  public BookController(BookMapper mapper, BookService service) {
    this.mapper = mapper;

    this.service = service;
  }

  @PostMapping
  public ResponseEntity<BookResponse> post(@RequestBody @Valid BookRequest request)
      throws URISyntaxException {
    Book book = mapper.map(request);

    service.save(book);

    String pathname = String.format("/api/books/%d", book.getId());

    URI uri = new URI(pathname);

    BookResponse response = mapper.map(book);

    return ResponseEntity.created(uri).body(response);
  }
}
