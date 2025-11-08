package com.bookmark.booklist.application;

import com.bookmark.booklist.domain.BookList;
import com.bookmark.user.domain.UserId;

public interface BookListService {
  BookList createBookList(UserId user, String title, String description);
}
