package com.bookmark.review_service.application;

import com.bookmark.review_service.domain.AverageRating;
import com.bookmark.review_service.domain.Paged;
import com.bookmark.review_service.domain.Pagination;
import com.bookmark.review_service.domain.Rating;
import com.bookmark.review_service.domain.Review;

public interface ReviewService {
  Review create(String userId, String bookId, int rating, String text);

  AverageRating queryAverageRatingByBook(String bookId);

  Paged<Review> queryBookReviews(String bookId, Pagination pagination);

  Paged<Review> queryUserReviews(String userId, Pagination pagination);
}
