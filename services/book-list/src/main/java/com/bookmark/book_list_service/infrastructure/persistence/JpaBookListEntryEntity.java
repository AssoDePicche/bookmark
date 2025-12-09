package com.bookmark.book_list_service.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "book_list_entry")
public class JpaBookListEntryEntity {
  @Id private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_list_id", nullable = false)
  private JpaBookListEntity bookList;

  @Column(nullable = false) private UUID book;

  private String notes;

  private boolean containSpoilers;

  private LocalDateTime dateAdded;

  public JpaBookListEntryEntity() {}

  public JpaBookListEntryEntity(UUID id, JpaBookListEntity bookList, UUID book, String notes,
      boolean containSpoilers, LocalDateTime dateAdded) {
    this.id = id;

    this.bookList = bookList;

    this.book = book;

    this.notes = notes;

    this.containSpoilers = containSpoilers;

    this.dateAdded = dateAdded;
  }

  public UUID getId() {
    return id;
  }

  public JpaBookListEntity getBookList() {
    return bookList;
  }

  public UUID getBook() {
    return book;
  }

  public String getNotes() {
    return notes;
  }

  public boolean getContainSpoilers() {
    return containSpoilers;
  }

  public LocalDateTime getDateAdded() {
    return dateAdded;
  }
}
