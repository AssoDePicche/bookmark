package com.bookmark.review_service.infrastructure;

import com.bookmark.review_service.application.AddReviewUseCase;
import com.bookmark.review_service.application.ReviewRequest;
import com.bookmark.review_service.application.ReviewResponse;
import com.bookmark.review_service.domain.BookId;
import com.bookmark.review_service.domain.Review;
import com.bookmark.review_service.domain.ReviewRepository;
import com.bookmark.review_service.domain.UserId;
import org.springframework.stereotype.Service;

@Service
public class AddReviewUseCaseImpl implements AddReviewUseCase {
  private final ReviewRepository repository;

  public AddReviewUseCaseImpl(ReviewRepository repository) {
    this.repository = repository;
  }

  @Override
  public ReviewResponse execute(ReviewRequest request) {
    var user = new UserId(request.user());

    var book = new BookId(request.book());

    var entity =
        new Review(repository.nextIdentity(), user, book, request.rating(), request.text());

    repository.save(entity);

    return ReviewMapper.map(entity);
  }
}
