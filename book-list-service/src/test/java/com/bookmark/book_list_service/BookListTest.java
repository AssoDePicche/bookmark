package com.bookmark.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bookmark.booklist.domain.BookList;
import com.bookmark.booklist.domain.BookListVisibility;
import com.bookmark.booklist.domain.Notes;
import com.bookmark.user.domain.UserId;
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
    UserId user = new UserId();

    String title = faker.lorem().sentence();

    String description = faker.lorem().sentence();

    BookList list = new BookList(user, title, description);

    assertTrue(list.getVisibility() == BookListVisibility.PRIVATE);
  }
}
