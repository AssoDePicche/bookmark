package com.bookmark.book.infrastructure;

import com.bookmark.book.application.BookSearch;
import com.bookmark.book.application.BookSearchResult;
import com.bookmark.book.domain.Isbn;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OpenLibraryBookSearch implements BookSearch {
  private static final String URL = "https://openlibrary.org/";

  private final RestClient client;

  public OpenLibraryBookSearch(RestClient.Builder builder) {
    this.client = builder.baseUrl(URL).build();
  }

  @Override
  public BookSearchResult execute(Isbn isbn) {
    var URI = String.format("/isbn/{%s}.json", isbn);

    return client.get().uri(URI).retrieve().body(BookSearchResult.class);
  }
}
