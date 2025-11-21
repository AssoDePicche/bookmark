package com.bookmark.book_list_service.infrastructure.persistence;

import com.bookmark.book_list_service.domain.BookList;
import com.bookmark.book_list_service.domain.BookListId;
import com.bookmark.book_list_service.domain.BookListVisibility;
import com.bookmark.book_list_service.domain.UserId;

public class JpaBookListEntityMapper {
  public static JpaBookListEntity map(BookList bookList) {
    var entity = new JpaBookListEntity(bookList.getId().value(), bookList.getUser().value(),
        bookList.getTitle().toString(), bookList.getDescription().toString(),
        bookList.getVisibility().toString());

    bookList.getEntries()
        .stream()
        .map(entry -> JpaBookListEntryEntityMapper.map(entry))
        .forEach(entry -> entity.addEntry(entry));

    return entity;
  }

  public static BookList map(JpaBookListEntity entity) {
    var id = new BookListId(entity.getId());

    var user = new UserId(entity.getUser());

    var visibility = BookListVisibility.from(entity.getVisibility());

    var bookList = new BookList(id, user, entity.getTitle(), entity.getDescription(), visibility);

    entity.getEntries()
        .stream()
        .map(entry -> JpaBookListEntryEntityMapper.map(entry, bookList))
        .forEach(entry -> bookList.addEntry(entry));

    return bookList;
  }
}
