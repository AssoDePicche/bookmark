package com.bookmark.review_service.application;

import com.bookmark.review_service.domain.Paged;
import com.bookmark.review_service.domain.Pagination;

public interface GetBookReviewsUseCase {
  Paged<ReviewResponse> execute(String bookId, Pagination pagination);
}
