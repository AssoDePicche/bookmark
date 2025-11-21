package com.bookmark.review_service.infrastructure.persistence;

import com.bookmark.review_service.domain.BookId;
import com.bookmark.review_service.domain.Review;
import com.bookmark.review_service.domain.ReviewId;
import com.bookmark.review_service.domain.UserId;

public class JpaReviewEntityMapper {
  public static JpaReviewEntity map(Review review) {
    return new JpaReviewEntity(review.getId().value(), review.getUser().value(),
        review.getBook().value(), review.getRating().value(), review.getText().toString(),
        review.getDateAdded());
  }

  public static Review map(JpaReviewEntity entity) {
    var id = new ReviewId(entity.getId());

    var user = new UserId(entity.getUser());

    var book = new BookId(entity.getBook());

    return new Review(id, user, book, entity.getRating(), entity.getText(), entity.getDateAdded());
  }
}
