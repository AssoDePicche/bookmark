package com.bookmark.book_list_service.infrastructure;

import com.bookmark.book_list_service.application.BookListResponse;
import com.bookmark.book_list_service.application.GetBookListsUseCase;
import com.bookmark.book_list_service.domain.BookList;
import com.bookmark.book_list_service.domain.BookListRepository;
import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.domain.Pagination;
import org.springframework.stereotype.Service;

@Service
public class GetBookListsUseCaseImpl implements GetBookListsUseCase {
  private final BookListRepository repository;

  public GetBookListsUseCaseImpl(BookListRepository repository) {
    this.repository = repository;
  }

  @Override
  public Paged<BookListResponse> execute(Pagination pagination) {
    Paged<BookList> entities = repository.findAll(pagination);

    return BookListMapper.map(entities);
  }
}
