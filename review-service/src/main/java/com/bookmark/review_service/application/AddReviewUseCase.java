package com.bookmark.review_service.application;

public interface AddReviewUseCase {
  ReviewResponse execute(ReviewRequest request);
}
