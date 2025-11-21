package com.bookmark.book_list_service.infrastructure.persistence;

import com.bookmark.book_list_service.domain.BookId;
import com.bookmark.book_list_service.domain.BookList;
import com.bookmark.book_list_service.domain.BookListEntry;
import com.bookmark.book_list_service.domain.BookListEntryId;

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
