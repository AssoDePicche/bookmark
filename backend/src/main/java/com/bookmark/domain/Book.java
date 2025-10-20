package com.bookmark.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Book {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

  private String title;

  private String description;

  private String genre;

  private LocalDate publicationDate;

  public Book() {}

  public Book(String title, String description, String genre, LocalDate publicationDate) {
    this.title = title;
    this.description = description;
    this.genre = genre;
    this.publicationDate = publicationDate;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getGenre() {
    return genre;
  }

  public LocalDate getPublicationDate() {
    return publicationDate;
  }
}
