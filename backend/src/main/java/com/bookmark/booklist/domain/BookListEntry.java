package com.bookmark.booklist.domain;

import com.bookmark.catalog.domain.BookId;
import java.time.LocalDateTime;

public class BookListEntry {
  private BookListEntryId id;

  private BookId book;

  private Notes notes;

  private LocalDateTime dateAdded;

  public BookListEntry(BookId book, String notes, boolean containSpoilers) {
    this(new BookListEntryId(), book, notes, containSpoilers, LocalDateTime.now());
  }

  public BookListEntry(BookListEntryId id, BookId book, String notes, boolean containSpoilers,
      LocalDateTime dateAdded) {
    this.id = id;

    this.book = book;

    this.notes = new Notes(notes, containSpoilers);

    this.dateAdded = dateAdded;
  }

  public BookListEntryId getId() {
    return id;
  }

  public BookId getBook() {
    return book;
  }

  public Notes getNotes() {
    return notes;
  }

  public LocalDateTime getDateAdded() {
    return dateAdded;
  }
}
