package com.bookmark.book_list_service.application;

import com.bookmark.book_list_service.domain.BookListEntry;

public interface AddBookListEntryUseCase {
  BookListEntryResponse execute(BookListEntryRequest request);
}
