package com.bookmark.book_list_service.infrastructure;

import com.bookmark.book_list_service.application.AddBookListEntryUseCase;
import com.bookmark.book_list_service.application.BookListEntryRequest;
import com.bookmark.book_list_service.application.BookListEntryResponse;
import com.bookmark.book_list_service.domain.BookId;
import com.bookmark.book_list_service.domain.BookList;
import com.bookmark.book_list_service.domain.BookListEntry;
import com.bookmark.book_list_service.domain.BookListEntryRepository;
import com.bookmark.book_list_service.domain.BookListId;
import com.bookmark.book_list_service.domain.BookListRepository;
import com.bookmark.book_list_service.domain.exception.NotFoundException;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class AddBookListEntryUseCaseImpl implements AddBookListEntryUseCase {
  private final BookListRepository lists;

  private final BookListEntryRepository entries;

  public AddBookListEntryUseCaseImpl(BookListRepository lists, BookListEntryRepository entries) {
    this.lists = lists;

    this.entries = entries;
  }

  @Override
  public BookListEntryResponse execute(BookListEntryRequest request) {
    var bookListId = new BookListId(request.bookListId());

    BookList bookList = lists.findById(bookListId).orElseThrow(() -> {
      var message = String.format("Book List Not Found With ID '%s'", request.bookListId());

      throw new NotFoundException(message);
    });

    var entity = bookList.addEntry(entries.nextIdentity(), request.bookId(), request.notes(),
        request.containSpoilers(), LocalDateTime.now());

    lists.save(bookList);

    return BookListEntryMapper.map(entity);
  }
}
