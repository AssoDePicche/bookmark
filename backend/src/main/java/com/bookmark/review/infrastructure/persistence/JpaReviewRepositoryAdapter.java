package com.bookmark.review.infrastructure.persistence;

import com.bookmark.catalog.domain.BookId;
import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;
import com.bookmark.common.infrastructure.mapper.PageMapper;
import com.bookmark.review.domain.Review;
import com.bookmark.review.domain.ReviewId;
import com.bookmark.review.domain.ReviewRepository;
import com.bookmark.user.domain.UserId;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class JpaReviewRepositoryAdapter implements ReviewRepository {
  private final JpaReviewRepository repository;

  public JpaReviewRepositoryAdapter(JpaReviewRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Review> findByBook(BookId book) {
    return repository.findByBook(book.value()).stream().map(JpaReviewEntityMapper::map).toList();
  }

  @Override
  public Paged<Review> findByBook(BookId book, Pagination pagination) {
    Pageable pageable = PageMapper.map(pagination);

    Page<JpaReviewEntity> page = repository.findByBook(book.value(), pageable);

    return PageMapper.map(page, JpaReviewEntityMapper::map);
  }

  @Override
  public Paged<Review> findByUser(UserId user, Pagination pagination) {
    Pageable pageable = PageMapper.map(pagination);

    Page<JpaReviewEntity> page = repository.findByUser(user.value(), pageable);

    return PageMapper.map(page, JpaReviewEntityMapper::map);
  }

  @Override
  public ReviewId nextIdentity() {
    return new ReviewId();
  }

  @Override
  public Review save(Review review) {
    JpaReviewEntity entity = JpaReviewEntityMapper.map(review);

    repository.save(entity);

    return JpaReviewEntityMapper.map(entity);
  }
}
