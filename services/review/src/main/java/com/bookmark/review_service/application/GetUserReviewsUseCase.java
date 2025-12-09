package com.bookmark.review_service.application;

import com.bookmark.review_service.domain.Paged;
import com.bookmark.review_service.domain.Pagination;

public interface GetUserReviewsUseCase {
  Paged<ReviewResponse> execute(String userId, Pagination pagination);
}
