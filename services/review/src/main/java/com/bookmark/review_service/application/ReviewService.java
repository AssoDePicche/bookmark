package com.bookmark.review_service.application;

import com.bookmark.review_service.domain.AverageRating;
import com.bookmark.review_service.domain.Paged;
import com.bookmark.review_service.domain.Pagination;
import com.bookmark.review_service.domain.Rating;
import com.bookmark.review_service.domain.Review;

public interface ReviewService {
  ReviewResponse create(ReviewRequest request);

  AverageRating queryAverageRatingByBook(String bookId);

  Paged<ReviewResponse> queryBookReviews(String bookId, Pagination pagination);

  Paged<ReviewResponse> queryUserReviews(String userId, Pagination pagination);
}
