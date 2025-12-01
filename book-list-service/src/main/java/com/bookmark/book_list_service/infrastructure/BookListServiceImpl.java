package com.bookmark.book_list_service.infrastructure;

import com.bookmark.book_list_service.application.AddBookListEntryUseCase;
import com.bookmark.book_list_service.application.BookListEntryRequest;
import com.bookmark.book_list_service.application.BookListEntryResponse;
import com.bookmark.book_list_service.application.BookListRequest;
import com.bookmark.book_list_service.application.BookListResponse;
import com.bookmark.book_list_service.application.BookListService;
import com.bookmark.book_list_service.application.CreateBookListUseCase;
import com.bookmark.book_list_service.application.GetBookListEntriesUseCase;
import com.bookmark.book_list_service.application.GetBookListsUseCase;
import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.domain.Pagination;
import org.springframework.stereotype.Service;

@Service
public class BookListServiceImpl implements BookListService {
  private final AddBookListEntryUseCase addEntry;

  private final CreateBookListUseCase createBookList;

  private final GetBookListEntriesUseCase getEntries;

  private final GetBookListsUseCase getBookLists;

  public BookListServiceImpl(AddBookListEntryUseCase addEntry, CreateBookListUseCase createBookList,
      GetBookListEntriesUseCase getEntries, GetBookListsUseCase getBookLists) {
    this.addEntry = addEntry;

    this.createBookList = createBookList;

    this.getEntries = getEntries;

    this.getBookLists = getBookLists;
  }

  @Override
  public BookListEntryResponse addBookListEntry(BookListEntryRequest request) {
    return addEntry.execute(request);
  }

  @Override
  public BookListResponse create(BookListRequest request) {
    return createBookList.execute(request);
  }

  @Override
  public Paged<BookListEntryResponse> query(String bookListId, Pagination pagination) {
    return getEntries.execute(bookListId, pagination);
  }

  @Override
  public Paged<BookListResponse> query(Pagination pagination) {
    return getBookLists.execute(pagination);
  }
}
