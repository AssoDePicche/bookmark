package com.bookmark.review.domain;

import com.bookmark.catalog.domain.BookId;
import com.bookmark.user.domain.UserId;
import java.time.LocalDateTime;

public class Review {
  private ReviewId id;

  private UserId user;

  private BookId book;

  private Rating rating;

  private Text text;

  private LocalDateTime dateAdded;

  public Review(UserId user, BookId book, int rating, String text) {
    this(new ReviewId(), user, book, rating, text, LocalDateTime.now());
  }

  public Review(
      ReviewId id, UserId user, BookId book, int rating, String text, LocalDateTime dateAdded) {
    this.id = id;

    this.user = user;

    this.book = book;

    this.rating = new Rating(rating);

    this.text = new Text(text);

    this.dateAdded = dateAdded;
  }

  public ReviewId getId() {
    return id;
  }

  public UserId getUser() {
    return user;
  }

  public BookId getBook() {
    return book;
  }

  public Rating getRating() {
    return rating;
  }

  public Text getText() {
    return text;
  }

  public LocalDateTime getDateAdded() {
    return dateAdded;
  }
}
