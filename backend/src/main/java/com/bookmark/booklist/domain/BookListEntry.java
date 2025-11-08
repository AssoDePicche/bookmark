package com.bookmark.booklist.domain;

import com.bookmark.book.domain.BookId;
import java.time.LocalDateTime;

public record BookListEntry(BookId book, Notes notes, LocalDateTime dateAdded) {
  public BookListEntry {
    if (null == book) {
      throw new IllegalArgumentException("Book List Entry BookId Must Not Be Null");
    }

    if (null == notes) {
      throw new IllegalArgumentException("Book List Entry Notes Must Not Be Null");
    }

    if (null == dateAdded) {
      throw new IllegalArgumentException("Book List Entry Date Added Must Not Be Null");
    }
  }

  public BookListEntry(BookId book, Notes notes) {
    this(book, notes, LocalDateTime.now());
  }
}
