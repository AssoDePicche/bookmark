package com.bookmark.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Book {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

  @AttributeOverride(name = "value", column = @Column(name = "isbn", unique = true))
  @Embedded
  private ISBN isbn;

  @AttributeOverride(name = "value", column = @Column(name = "title", unique = true))
  @Embedded
  private Title title;

  @AttributeOverride(name = "value", column = @Column(name = "description"))
  @Embedded
  private Description description;

  private String genre;

  @Column(name = "publication_date") private LocalDate publicationDate;

  @CreatedDate private LocalDateTime createdAt;

  @LastModifiedDate private LocalDateTime updatedAt;

  public Book() {}

  public Book(
      String isbn, String title, String description, String genre, LocalDate publicationDate) {
    this.isbn = new ISBN(isbn);
    this.title = new Title(title);
    this.description = new Description(description);
    this.genre = genre;
    this.publicationDate = publicationDate;
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
