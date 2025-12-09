package com.bookmark.book_list_service.application;

import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.domain.Pagination;

public interface BookListService {
  BookListEntryResponse addBookListEntry(BookListEntryRequest request);

  BookListResponse create(BookListRequest request);

  Paged<BookListEntryResponse> query(String bookListId, Pagination pagination);

  Paged<BookListResponse> query(Pagination pagination);
}
