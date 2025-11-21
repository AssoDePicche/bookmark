package com.bookmark.review_service.interfaces;

import com.bookmark.review_service.application.ReviewService;
import com.bookmark.review_service.domain.AverageRating;
import com.bookmark.review_service.domain.Paged;
import com.bookmark.review_service.domain.Pagination;
import com.bookmark.review_service.domain.Review;
import com.bookmark.review_service.infrastructure.mapper.PageMapper;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/reviews")
@RestController
@Validated
public class ReviewController {
  private final ReviewService service;

  public ReviewController(ReviewService service) {
    this.service = service;
  }

  @GetMapping("/{book}/average")
  public ResponseEntity<AverageRating> getBookAverageRating(String book) {
    AverageRating rating = service.queryAverageRatingByBook(book);

    return ResponseEntity.ok(rating);
  }

  @GetMapping("/{book}")
  public ResponseEntity<Paged<ReviewResponse>> getBookReviews(@PathVariable String book,
      @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
    Pagination pagination = PageMapper.map(pageable);

    Paged<Review> page = service.queryBookReviews(book, pagination);

    Paged<ReviewResponse> response = ReviewMapper.map(page);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{user}")
  public ResponseEntity<Paged<ReviewResponse>> getUserReviews(@PathVariable String user,
      @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
    Pagination pagination = PageMapper.map(pageable);

    Paged<Review> page = service.queryUserReviews(user, pagination);

    Paged<ReviewResponse> response = ReviewMapper.map(page);

    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<ReviewResponse> post(@RequestBody @Valid ReviewRequest request)
      throws URISyntaxException {
    Review review =
        service.create(request.user(), request.book(), request.rating(), request.text());

    String pathname = "/api/reviews/" + review.getId();

    var uri = new URI(pathname);

    ReviewResponse response = ReviewMapper.map(review);

    return ResponseEntity.created(uri).body(response);
  }
}
