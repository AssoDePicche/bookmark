package com.bookmark.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Book {
  private Long id;

  private ISBN isbn;

  private Title title;

  private Description description;

  private String genre;

  private LocalDate publicationDate;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public Book(
      String isbn, String title, String description, String genre, LocalDate publicationDate) {
    this.isbn = new ISBN(isbn);
    this.title = new Title(title);
    this.description = new Description(description);
    this.genre = genre;
    this.publicationDate = publicationDate;
  }

  public Book(Long id, String isbn, String title, String description, String genre,
      LocalDate publicationDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.isbn = new ISBN(isbn);
    this.title = new Title(title);
    this.description = new Description(description);
    this.genre = genre;
    this.publicationDate = publicationDate;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public ISBN getISBN() {
    return isbn;
  }

  public Title getTitle() {
    return title;
  }

  public Description getDescription() {
    return description;
  }

  public String getGenre() {
    return genre;
  }

  public LocalDate getPublicationDate() {
    return publicationDate;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
