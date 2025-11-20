package com.bookmark.review.application;

import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;
import com.bookmark.review.domain.AverageRating;
import com.bookmark.review.domain.Rating;
import com.bookmark.review.domain.Review;

public interface ReviewService {
  Review create(String userId, String bookId, int rating, String text);

  AverageRating queryAverageRatingByBook(String bookId);

  Paged<Review> queryBookReviews(String bookId, Pagination pagination);

  Paged<Review> queryUserReviews(String userId, Pagination pagination);
}
