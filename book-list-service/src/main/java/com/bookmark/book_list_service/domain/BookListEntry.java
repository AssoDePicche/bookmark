package com.bookmark.book_list_service.domain;

import java.time.LocalDateTime;

public class BookListEntry {
  private BookListEntryId id;

  private BookList bookList;

  private BookId book;

  private Notes notes;

  private LocalDateTime dateAdded;

  public BookListEntry(
      BookListEntryId id, BookList bookList, BookId book, Notes notes, LocalDateTime dateAdded) {
    this.id = id;

    this.bookList = bookList;

    this.book = book;

    this.notes = notes;

    this.dateAdded = dateAdded;
  }

  public BookListEntryId getId() {
    return id;
  }

  public BookList getBookList() {
    return bookList;
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
