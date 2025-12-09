package com.bookmark.book_list_service.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookList {
  private BookListId id;

  private UserId user;

  private Title title;

  private Description description;

  private List<BookListEntry> entries = new ArrayList<>();

  private BookListVisibility visibility;

  public BookList(
      BookListId id, UserId user, String title, String description, BookListVisibility visibility) {
    this.id = id;

    this.user = user;

    this.title = new Title(title);

    this.description = new Description(description);

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

  public BookListEntry addEntry(BookListEntryId id, String book, String notes,
      boolean containSpoilers, LocalDateTime dateAdded) {
    var entry =
        new BookListEntry(id, this, new BookId(book), new Notes(notes, containSpoilers), dateAdded);

    entries.add(entry);

    return entry;
  }

  public BookListEntry addEntry(BookListEntry entry) {
    entries.add(entry);

    return entry;
  }
}
