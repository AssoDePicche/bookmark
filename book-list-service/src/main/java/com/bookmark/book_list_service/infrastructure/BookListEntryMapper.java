package com.bookmark.book_list_service.infrastructure;

import com.bookmark.book_list_service.application.BookListEntryRequest;
import com.bookmark.book_list_service.application.BookListEntryResponse;
import com.bookmark.book_list_service.domain.BookListEntry;
import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.infrastructure.mapper.PageMapper;
import java.time.format.DateTimeFormatter;

public class BookListEntryMapper {
  public static BookListEntryResponse map(BookListEntry entry) {
    return new BookListEntryResponse(entry.getBook().toString(), entry.getNotes().toString(),
        entry.getNotes().containSpoilers(),
        entry.getDateAdded().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
  }

  public static Paged<BookListEntryResponse> map(Paged<BookListEntry> pagedBookListEntries) {
    return PageMapper.map(pagedBookListEntries, (entry) -> map(entry));
  }
}
