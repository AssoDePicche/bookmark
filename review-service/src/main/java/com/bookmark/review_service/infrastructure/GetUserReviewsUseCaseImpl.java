package com.bookmark.review_service.infrastructure;

import com.bookmark.review_service.application.GetUserReviewsUseCase;
import com.bookmark.review_service.application.ReviewResponse;
import com.bookmark.review_service.domain.Paged;
import com.bookmark.review_service.domain.Pagination;
import com.bookmark.review_service.domain.Review;
import com.bookmark.review_service.domain.ReviewRepository;
import com.bookmark.review_service.domain.UserId;

public class GetUserReviewsUseCaseImpl implements GetUserReviewsUseCase {
  private final ReviewRepository repository;

  public GetUserReviewsUseCaseImpl(ReviewRepository repository) {
    this.repository = repository;
  }

  @Override
  public Paged<ReviewResponse> execute(String userId, Pagination pagination) {
    var user = new UserId(userId);

    Paged<Review> entities = repository.findByUser(user, pagination);

    return ReviewMapper.map(entities);
  }
}
