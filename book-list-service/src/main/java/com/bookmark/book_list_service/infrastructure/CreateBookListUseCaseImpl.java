package com.bookmark.book_list_service.infrastructure;

import com.bookmark.book_list_service.application.BookListRequest;
import com.bookmark.book_list_service.application.BookListResponse;
import com.bookmark.book_list_service.application.CreateBookListUseCase;
import com.bookmark.book_list_service.domain.BookList;
import com.bookmark.book_list_service.domain.BookListRepository;
import com.bookmark.book_list_service.domain.BookListVisibility;
import com.bookmark.book_list_service.domain.UserId;

public class CreateBookListUseCaseImpl implements CreateBookListUseCase {
  private final BookListRepository repository;

  public CreateBookListUseCaseImpl(BookListRepository repository) {
    this.repository = repository;
  }

  @Override
  public BookListResponse execute(BookListRequest request) {
    var entity = new BookList(repository.nextIdentity(), new UserId(request.user()),
        request.title(), request.description(), BookListVisibility.PRIVATE);

    repository.save(entity);

    return new BookListResponse(request.title(), request.description());
  }
}
