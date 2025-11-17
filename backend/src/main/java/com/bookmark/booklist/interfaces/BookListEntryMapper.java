package com.bookmark.booklist.interfaces;

import com.bookmark.booklist.domain.BookListEntry;
import com.bookmark.common.domain.Paged;
import com.bookmark.common.infrastructure.mapper.PageMapper;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
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
