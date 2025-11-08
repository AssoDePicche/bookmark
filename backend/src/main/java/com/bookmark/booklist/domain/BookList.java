package com.bookmark.booklist.domain;

import com.bookmark.book.domain.BookId;
import com.bookmark.user.domain.UserId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookList {
  private BookListId id;

  private UserId user;

  private Title title;

  private Description description;

  private List<BookListEntry> entries;

  private BookListVisibility visibility;

  public BookList(UserId user, String title, String description) {
    this(new BookListId(), user, title, description, BookListVisibility.PRIVATE);
  }

  public BookList(
      BookListId id, UserId user, String title, String description, BookListVisibility visibility) {
    this.id = id;

    this.user = user;

    this.title = new Title(title);

    this.description = new Description(description);

    this.entries = new ArrayList<>();

    this.visibility = visibility;
  }

  public BookListId getId() {
    return id;
  }

  public UserId getUser() {
    return user;
  }

  public Title getTitle() {
    return title;
  }

  public Description getDescription() {
    return description;
  }

  public List<BookListEntry> getEntries() {
    return entries;
  }

  public BookListVisibility getVisibility() {
    return visibility;
  }

  public void changeDescription(String description) {
    this.description = new Description(description);
  }

  public void changeVisibility(BookListVisibility visibility) {
    this.visibility = visibility;
  }

  public void addEntry(BookId book, String notes, boolean containSpoilers) {
    Notes entryNotes = new Notes(notes, containSpoilers);

    BookListEntry entry = new BookListEntry(book, entryNotes);

    entries.add(entry);
  }
}
