package com.bookmark.book_list_service.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

public class BookListTest {
  private static final Faker faker = new Faker();

  @Test
  public void emptyNotesShouldNotContainSpoilers() {
    Notes notes = new Notes();

    assertFalse(notes.containSpoilers());
  }

  @Test
  public void bookListShouldBePrivateByDefault() {
    UserId book_list_service = new UserId();

    String title = faker.lorem().sentence();

    String description = faker.lorem().sentence();

    BookList list = new BookList(book_list_service, title, description);

    assertTrue(list.getVisibility() == BookListVisibility.PRIVATE);
  }
}
