package com.bookmark.review_service.infrastructure;

import com.bookmark.review_service.application.AddReviewUseCase;
import com.bookmark.review_service.application.GetBookAverageRatingUseCase;
import com.bookmark.review_service.application.GetBookReviewsUseCase;
import com.bookmark.review_service.application.GetUserReviewsUseCase;
import com.bookmark.review_service.application.ReviewRequest;
import com.bookmark.review_service.application.ReviewResponse;
import com.bookmark.review_service.application.ReviewService;
import com.bookmark.review_service.domain.AverageRating;
import com.bookmark.review_service.domain.BookId;
import com.bookmark.review_service.domain.Paged;
import com.bookmark.review_service.domain.Pagination;
import com.bookmark.review_service.domain.Rating;
import com.bookmark.review_service.domain.Review;
import com.bookmark.review_service.domain.UserId;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
  private final AddReviewUseCase addReview;

  private final GetBookAverageRatingUseCase getBookAverageRating;

  private final GetBookReviewsUseCase getBookReviews;

  private final GetUserReviewsUseCase getUserReviews;

  public ReviewServiceImpl(AddReviewUseCase addReview,
      GetBookAverageRatingUseCase getBookAverageRating, GetBookReviewsUseCase getBookReviews,
      GetUserReviewsUseCase getUserReviews) {
    this.addReview = addReview;

    this.getBookAverageRating = getBookAverageRating;

    this.getBookReviews = getBookReviews;

    this.getUserReviews = getUserReviews;
  }

  @Override
  public ReviewResponse create(ReviewRequest request) {
    return addReview.execute(request);
  }

  @Override
  public AverageRating queryAverageRatingByBook(String bookId) {
    return getBookAverageRating.execute(bookId);
  }

  @Override
  public Paged<ReviewResponse> queryBookReviews(String bookId, Pagination pagination) {
    return getBookReviews.execute(bookId, pagination);
  }

  @Override
  public Paged<ReviewResponse> queryUserReviews(String userId, Pagination pagination) {
    return getUserReviews.execute(userId, pagination);
  }
}
