package com.bookmark.catalog_service.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Book {
  private BookId id;

  private Isbn isbn;

  private Title title;

  private Description description;

  private String genre;

  private LocalDate publicationDate;

  public Book(
      String isbn, String title, String description, String genre, LocalDate publicationDate) {
    this(new BookId(), isbn, title, description, genre, publicationDate);
  }

  public Book(BookId id, String isbn, String title, String description, String genre,
      LocalDate publicationDate) {
    this.id = id;
    this.isbn = new Isbn(isbn);
    this.title = new Title(title);
    this.description = new Description(description);
    this.genre = genre;
    this.publicationDate = publicationDate;
  }

  public BookId getId() {
    return id;
  }

  public Isbn getIsbn() {
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
}
