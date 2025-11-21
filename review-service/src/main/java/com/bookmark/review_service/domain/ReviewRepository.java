package com.bookmark.review_service.domain;

import com.bookmark.review_service.domain.BookId;
import com.bookmark.review_service.domain.Paged;
import com.bookmark.review_service.domain.Pagination;
import com.bookmark.review_service.domain.UserId;
import java.util.List;

public interface ReviewRepository {
  List<Review> findByBook(BookId book);

  Paged<Review> findByBook(BookId book, Pagination pagination);

  Paged<Review> findByUser(UserId user, Pagination pagination);

  ReviewId nextIdentity();

  Review save(Review review_service);
}
