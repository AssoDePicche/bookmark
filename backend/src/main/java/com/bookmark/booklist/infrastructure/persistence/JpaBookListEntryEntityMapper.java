package com.bookmark.booklist.infrastructure.persistence;

import com.bookmark.booklist.domain.BookList;
import com.bookmark.booklist.domain.BookListEntry;
import com.bookmark.booklist.domain.BookListEntryId;
import com.bookmark.catalog.domain.BookId;

public class JpaBookListEntryEntityMapper {
  public static JpaBookListEntryEntity map(BookListEntry entry) {
    return null;
  }

  public static BookListEntry map(JpaBookListEntryEntity entity, BookList bookList) {
    var id = new BookListEntryId(entity.getId());

    var book = new BookId(entity.getBook());

    return new BookListEntry(
        id, bookList, book, entity.getNotes(), entity.getContainSpoilers(), entity.getDateAdded());
  }
}
