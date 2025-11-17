package com.bookmark.booklist.interfaces;

import com.bookmark.booklist.domain.BookList;
import com.bookmark.common.domain.Paged;
import com.bookmark.common.infrastructure.mapper.PageMapper;
import org.springframework.stereotype.Component;

@Component
public class BookListMapper {
  public static BookListResponse map(BookList bookList) {
    return new BookListResponse(
        bookList.getTitle().toString(), bookList.getDescription().toString());
  }

  public static Paged<BookListResponse> map(Paged<BookList> pagedBookLists) {
    return PageMapper.map(pagedBookLists, (bookList) -> map(bookList));
  }
}
