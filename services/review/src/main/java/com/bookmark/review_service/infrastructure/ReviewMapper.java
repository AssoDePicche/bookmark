package com.bookmark.review_service.infrastructure;

import com.bookmark.review_service.application.ReviewRequest;
import com.bookmark.review_service.application.ReviewResponse;
import com.bookmark.review_service.domain.Paged;
import com.bookmark.review_service.domain.Review;
import com.bookmark.review_service.infrastructure.mapper.PageMapper;
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
