package com.bookmark.catalog_service.infrastructure;

import com.bookmark.catalog_service.application.BookSearch;
import com.bookmark.catalog_service.application.BookSearchResult;
import com.bookmark.catalog_service.domain.Isbn;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OpenLibraryBookSearch implements BookSearch {
  private final RestClient client;

  public OpenLibraryBookSearch(RestClient client) {
    this.client = client;
  }

  @Override
  public BookSearchResult execute(Isbn isbn) {
    var URI = String.format("/isbn/{%s}.json", isbn);

    var result = client.get().uri(URI).retrieve().body(OpenLibraryBookSearchResult.class);

    return new BookSearchResult(result.title(), result.publishDate(), result.physicalFormat(),
        result.publishers(), result.isbn10(), result.isbn13());
  }
}
