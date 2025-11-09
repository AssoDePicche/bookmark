package com.bookmark.book.application;

import com.bookmark.book.domain.Isbn;

public interface BookSearch {
  BookSearchResult execute(Isbn isbn);
}
