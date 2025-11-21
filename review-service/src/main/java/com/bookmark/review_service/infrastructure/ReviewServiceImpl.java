package com.bookmark.review_service.infrastructure;

import com.bookmark.review_service.application.ReviewService;
import com.bookmark.review_service.domain.AverageRating;
import com.bookmark.review_service.domain.BookId;
import com.bookmark.review_service.domain.Paged;
import com.bookmark.review_service.domain.Pagination;
import com.bookmark.review_service.domain.Rating;
import com.bookmark.review_service.domain.Review;
import com.bookmark.review_service.domain.ReviewRepository;
import com.bookmark.review_service.domain.UserId;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository repository;

  public ReviewServiceImpl(ReviewRepository repository) {
    this.repository = repository;
  }

  @Override
  public Review create(String userId, String bookId, int rating, String text) {
    var review =
        new Review(repository.nextIdentity(), new UserId(userId), new BookId(bookId), rating, text);

    repository.save(review);

    return review;
  }

  @Override
  public AverageRating queryAverageRatingByBook(String bookId) {
    List<Review> reviews = repository.findByBook(new BookId(bookId));

    int score = reviews.parallelStream().reduce(
        0, (accumulator, review) -> accumulator + review.getRating().value(), Integer::sum);

    return new AverageRating(score / reviews.size(), reviews.size());
  }

  @Override
  public Paged<Review> queryBookReviews(String bookId, Pagination pagination) {
    return repository.findByBook(new BookId(bookId), pagination);
  }

  @Override
  public Paged<Review> queryUserReviews(String userId, Pagination pagination) {
    return repository.findByUser(new UserId(userId), pagination);
  }
}
