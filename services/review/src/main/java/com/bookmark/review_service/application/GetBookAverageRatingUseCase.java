package com.bookmark.review_service.application;

import com.bookmark.review_service.domain.AverageRating;

public interface GetBookAverageRatingUseCase {
  AverageRating execute(String bookId);
}
