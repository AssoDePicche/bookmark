package com.bookmark.catalog_service.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "book")
public class JpaBookEntity {
  @Id private UUID id;

  @Column(name = "isbn", unique = true) private String isbn;

  @Column(name = "title", unique = true) private String title;

  private String description;

  private String genre;

  @Column(name = "publication_date") private LocalDate publicationDate;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false) @LastModifiedDate private LocalDateTime updatedAt;

  public JpaBookEntity() {}

  public JpaBookEntity(UUID id, String isbn, String title, String description, String genre,
      LocalDate publicationDate) {
    this.id = id;

    this.isbn = isbn;

    this.title = title;

    this.description = description;

    this.genre = genre;

    this.publicationDate = publicationDate;
  }

  public UUID getId() {
    return id;
  }

  public String getIsbn() {
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
