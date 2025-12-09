package com.bookmark.book_list_service.application;

import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.domain.Pagination;

public interface GetBookListsUseCase {
  Paged<BookListResponse> execute(Pagination pagination);
}
