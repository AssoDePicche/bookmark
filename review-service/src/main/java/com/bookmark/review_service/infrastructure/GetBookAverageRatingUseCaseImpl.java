package com.bookmark.review_service.infrastructure;

import com.bookmark.review_service.application.GetBookAverageRatingUseCase;
import com.bookmark.review_service.domain.AverageRating;
import com.bookmark.review_service.domain.BookId;
import com.bookmark.review_service.domain.Review;
import com.bookmark.review_service.domain.ReviewRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetBookAverageRatingUseCaseImpl implements GetBookAverageRatingUseCase {
  private final ReviewRepository repository;

  public GetBookAverageRatingUseCaseImpl(ReviewRepository repository) {
    this.repository = repository;
  }

  @Override
  public AverageRating execute(String bookId) {
    var book = new BookId(bookId);

    List<Review> reviews = repository.findByBook(book);

    int score = reviews.parallelStream().reduce(
        0, (accumulator, review) -> accumulator + review.getRating().value(), Integer::sum);

    return new AverageRating(score / reviews.size(), reviews.size());
  }
}
