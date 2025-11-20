package com.bookmark.review.interfaces;

import com.bookmark.common.domain.Paged;
import com.bookmark.common.infrastructure.mapper.PageMapper;
import com.bookmark.review.domain.Review;
import java.time.format.DateTimeFormatter;

public class ReviewMapper {
  public static ReviewResponse map(Review review) {
    return new ReviewResponse(review.getUser().toString(), review.getBook().toString(),
        review.getRating().value(), review.getText().toString(),
        review.getDateAdded().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
  }

  public static Paged<ReviewResponse> map(Paged<Review> pagedReviews) {
    return PageMapper.map(pagedReviews, (review) -> map(review));
  }
}
