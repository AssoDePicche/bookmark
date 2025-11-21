package com.bookmark.book_list_service.interfaces;

import com.bookmark.book_list_service.domain.BookList;
import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.infrastructure.mapper.PageMapper;

public class BookListMapper {
  public static BookListResponse map(BookList bookList) {
    return new BookListResponse(
        bookList.getTitle().toString(), bookList.getDescription().toString());
  }

  public static Paged<BookListResponse> map(Paged<BookList> pagedBookLists) {
    return PageMapper.map(pagedBookLists, (bookList) -> map(bookList));
  }
}
