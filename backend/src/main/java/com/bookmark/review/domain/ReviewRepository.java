package com.bookmark.review.domain;

import com.bookmark.catalog.domain.BookId;
import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;
import com.bookmark.user.domain.UserId;
import java.util.List;

public interface ReviewRepository {
  List<Review> findByBook(BookId book);

  Paged<Review> findByBook(BookId book, Pagination pagination);

  Paged<Review> findByUser(UserId user, Pagination pagination);

  ReviewId nextIdentity();

  Review save(Review review);
}
