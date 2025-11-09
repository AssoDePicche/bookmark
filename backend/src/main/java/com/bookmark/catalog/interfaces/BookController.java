package com.bookmark.catalog.interfaces;

import com.bookmark.catalog.application.BookService;
import com.bookmark.catalog.domain.Book;
import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;
import com.bookmark.common.infrastructure.mapper.PageMapper;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping
  public ResponseEntity<Paged<BookResponse>> get(
      @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
    Pagination pagination = PageMapper.map(pageable);

    Paged<Book> pagedBooks = service.query(pagination);

    Paged<BookResponse> response = BookMapper.map(pagedBooks);

    return ResponseEntity.ok(response);
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
