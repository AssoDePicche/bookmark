package com.bookmark.book_list_service.interfaces;

import com.bookmark.book_list_service.application.BookListEntryRequest;
import com.bookmark.book_list_service.application.BookListEntryResponse;
import com.bookmark.book_list_service.application.BookListRequest;
import com.bookmark.book_list_service.application.BookListResponse;
import com.bookmark.book_list_service.application.BookListService;
import com.bookmark.book_list_service.domain.BookList;
import com.bookmark.book_list_service.domain.BookListEntry;
import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.domain.Pagination;
import com.bookmark.book_list_service.infrastructure.mapper.PageMapper;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/lists")
@RestController
@Validated
public class BookListController {
  private final BookListService service;

  public BookListController(BookListService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Paged<BookListResponse>> get(
      @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
    Pagination pagination = PageMapper.map(pageable);

    Paged<BookListResponse> response = service.query(pagination);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Paged<BookListEntryResponse>> get(@PathVariable String bookListId,
      @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
    Pagination pagination = PageMapper.map(pageable);

    Paged<BookListEntryResponse> response = service.query(bookListId, pagination);

    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<BookListResponse> post(@RequestBody @Valid BookListRequest request)
      throws URISyntaxException {
    BookListResponse response = service.create(request);

    String pathname = "/api/lists/" + response.title();

    var uri = new URI(pathname);

    return ResponseEntity.created(uri).body(response);
  }

  @PostMapping("/{id}")
  public ResponseEntity<BookListEntryResponse> post(@PathVariable String id,
      @RequestBody @Valid BookListEntryRequest request) throws URISyntaxException {
    BookListEntryResponse response = service.addBookListEntry(request);

    String pathname = "/api/lists/" + id + "/" + response.book();

    var uri = new URI(pathname);

    return ResponseEntity.created(uri).body(response);
  }
}
