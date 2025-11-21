package com.bookmark.catalog.application;

import com.bookmark.catalog.domain.Isbn;

public interface BookSearch {
  BookSearchResult execute(Isbn isbn);
}
