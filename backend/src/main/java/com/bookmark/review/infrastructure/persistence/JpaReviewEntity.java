package com.bookmark.review.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "review")
public class JpaReviewEntity {
  @Id private UUID id;

  @Column(nullable = false) private UUID user;

  @Column(nullable = false) private UUID book;

  @Column(nullable = false) private int rating;

  private String text;

  private LocalDateTime dateAdded;

  public JpaReviewEntity() {}

  public JpaReviewEntity(
      UUID id, UUID user, UUID book, int rating, String text, LocalDateTime dateAdded) {
    this.id = id;

    this.user = user;

    this.book = book;
  }

  public UUID getId() {
    return id;
  }

  public UUID getUser() {
    return user;
  }

  public UUID getBook() {
    return book;
  }

  public int getRating() {
    return rating;
  }

  public String getText() {
    return text;
  }

  public LocalDateTime getDateAdded() {
    return dateAdded;
  }
}
