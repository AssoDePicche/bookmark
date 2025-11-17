package com.bookmark.booklist.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "book_list")
public class JpaBookListEntity {
  @Id private UUID id;

  @Column(nullable = false) private UUID user;

  private String title;

  private String description;

  private Set<Object> entries;

  private String visibility;

  public JpaBookListEntity() {}

  public JpaBookListEntity(UUID id, UUID user, String description, String visibility) {
    this.id = id;

    this.user = user;

    this.description = description;

    this.visibility = visibility;
  }
}
