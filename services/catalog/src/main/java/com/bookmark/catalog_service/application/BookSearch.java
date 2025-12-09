package com.bookmark.catalog_service.application;

import com.bookmark.catalog_service.domain.Isbn;

public interface BookSearch {
  BookSearchResult execute(Isbn isbn);
}
