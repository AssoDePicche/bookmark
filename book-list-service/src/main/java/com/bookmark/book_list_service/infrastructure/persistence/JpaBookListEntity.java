package com.bookmark.book_list_service.infrastructure.persistence;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "book_list")
public class JpaBookListEntity {
  @Id private UUID id;

  @Column(nullable = false) private UUID user;

  private String title;

  private String description;

  @OneToMany(mappedBy = "bookList", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<JpaBookListEntryEntity> entries = new HashSet<>();

  private String visibility;

  public JpaBookListEntity() {}

  public JpaBookListEntity(
      UUID id, UUID user, String title, String description, String visibility) {
    this.id = id;

    this.user = user;

    this.title = title;

    this.description = description;

    this.visibility = visibility;
  }

  public UUID getId() {
    return id;
  }

  public UUID getUser() {
    return user;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public void addEntry(JpaBookListEntryEntity entry) {
    entries.add(entry);
  }

  public Set<JpaBookListEntryEntity> getEntries() {
    return entries;
  }

  public String getVisibility() {
    return visibility;
  }
}
