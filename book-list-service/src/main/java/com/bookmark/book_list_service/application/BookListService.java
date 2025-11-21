package com.bookmark.book_list_service.application;

import com.bookmark.book_list_service.domain.BookList;
import com.bookmark.book_list_service.domain.BookListEntry;
import com.bookmark.book_list_service.domain.BookListId;
import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.domain.Pagination;

public interface BookListService {
  BookListEntry addBookToBookList(
      String bookListId, String bookId, String notes, boolean containSpoilers);

  BookList createBookList(String user, String title, String description);

  Paged<BookListEntry> query(String bookListId, Pagination pagination);

  Paged<BookList> query(Pagination pagination);
}
