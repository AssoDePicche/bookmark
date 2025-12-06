package com.bookmark.review_service.infrastructure;

import com.bookmark.review_service.application.GetBookReviewsUseCase;
import com.bookmark.review_service.application.ReviewResponse;
import com.bookmark.review_service.domain.BookId;
import com.bookmark.review_service.domain.Paged;
import com.bookmark.review_service.domain.Pagination;
import com.bookmark.review_service.domain.Review;
import com.bookmark.review_service.domain.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class GetBookReviewsUseCaseImpl implements GetBookReviewsUseCase {
  private final ReviewRepository repository;

  public GetBookReviewsUseCaseImpl(ReviewRepository repository) {
    this.repository = repository;
  }

  @Override
  public Paged<ReviewResponse> execute(String bookId, Pagination pagination) {
    var book = new BookId(bookId);

    Paged<Review> entities = repository.findByBook(book, pagination);

    return ReviewMapper.map(entities);
  }
}
