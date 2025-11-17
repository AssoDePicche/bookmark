package com.bookmark.booklist.application;

import com.bookmark.booklist.domain.BookList;
import com.bookmark.booklist.domain.BookListEntry;
import com.bookmark.booklist.domain.BookListId;
import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;

public interface BookListService {
  BookListEntry addBookToBookList(
      String bookListId, String bookId, String notes, boolean containSpoilers);

  BookList createBookList(String user, String title, String description);

  Paged<BookListEntry> query(String bookListId, Pagination pagination);

  Paged<BookList> query(Pagination pagination);
}
