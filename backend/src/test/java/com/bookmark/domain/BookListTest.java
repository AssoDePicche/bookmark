package com.bookmark.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;

import com.bookmark.list.domain.Notes;
import org.junit.jupiter.api.Test;

public class BookListTest {
  @Test
  public void emptyNotesShouldNotContainSpoilers() {
    Notes notes = new Notes();

    assertFalse(notes.containSpoilers());
  }
}
