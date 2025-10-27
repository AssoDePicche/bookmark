package com.bookmark.infrastructure.persistence;

import jakarta.persistence.Column;
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
public class JpaBook {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

  @Column(name = "isbn", unique = true) private String isbn;

  @Column(name = "title", unique = true) private String title;

  private String description;

  private String genre;

  @Column(name = "publication_date") private LocalDate publicationDate;

  @CreatedDate private LocalDateTime createdAt;

  @LastModifiedDate private LocalDateTime updatedAt;

  public JpaBook() {}

  public JpaBook(
      String isbn, String title, String description, String genre, LocalDate publicationDate) {
    this.isbn = isbn;
    this.title = title;
    this.description = description;
    this.genre = genre;
    this.publicationDate = publicationDate;
  }

  public Long getId() {
    return id;
  }

  public String getISBN() {
    return isbn;
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
