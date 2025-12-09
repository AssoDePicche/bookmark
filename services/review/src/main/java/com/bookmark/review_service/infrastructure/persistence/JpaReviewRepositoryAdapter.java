package com.bookmark.review_service.infrastructure.persistence;

import com.bookmark.review_service.domain.BookId;
import com.bookmark.review_service.domain.Paged;
import com.bookmark.review_service.domain.Pagination;
import com.bookmark.review_service.domain.Review;
import com.bookmark.review_service.domain.ReviewId;
import com.bookmark.review_service.domain.ReviewRepository;
import com.bookmark.review_service.domain.UserId;
import com.bookmark.review_service.infrastructure.mapper.PageMapper;
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
