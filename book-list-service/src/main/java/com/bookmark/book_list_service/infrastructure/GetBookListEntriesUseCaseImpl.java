package com.bookmark.book_list_service.infrastructure;

import com.bookmark.book_list_service.application.BookListEntryResponse;
import com.bookmark.book_list_service.application.GetBookListEntriesUseCase;
import com.bookmark.book_list_service.domain.BookList;
import com.bookmark.book_list_service.domain.BookListEntry;
import com.bookmark.book_list_service.domain.BookListEntryRepository;
import com.bookmark.book_list_service.domain.BookListId;
import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.domain.Pagination;
import org.springframework.stereotype.Service;

@Service
public class GetBookListEntriesUseCaseImpl implements GetBookListEntriesUseCase {
  private final BookListEntryRepository repository;

  public GetBookListEntriesUseCaseImpl(BookListEntryRepository repository) {
    this.repository = repository;
  }

  @Override
  public Paged<BookListEntryResponse> execute(String bookListId, Pagination pagination) {
    var id = new BookListId(bookListId);

    Paged<BookListEntry> entities = repository.findByBookList(id, pagination);

    return BookListEntryMapper.map(entities);
  }
}
