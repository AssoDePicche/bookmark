package com.bookmark.book_list_service.application;

import com.bookmark.book_list_service.domain.BookList;

public interface CreateBookListUseCase {
  BookListResponse execute(BookListRequest request);
}
