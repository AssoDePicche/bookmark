package com.bookmark.booklist.infrastructure;

import com.bookmark.booklist.application.BookListService;
import com.bookmark.booklist.domain.BookList;
import com.bookmark.booklist.domain.BookListEntry;
import com.bookmark.booklist.domain.BookListEntryRepository;
import com.bookmark.booklist.domain.BookListId;
import com.bookmark.booklist.domain.BookListRepository;
import com.bookmark.catalog.domain.BookId;
import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;
import com.bookmark.common.domain.exception.DuplicateEntryException;
import com.bookmark.common.domain.exception.NotFoundException;
import com.bookmark.user.domain.UserId;
import org.springframework.stereotype.Service;

@Service
public class BookListServiceImpl implements BookListService {
  private final BookListEntryRepository bookListEntryRepository;

  private final BookListRepository bookListRepository;

  public BookListServiceImpl(
      BookListEntryRepository bookListEntryRepository, BookListRepository bookListRepository) {
    this.bookListEntryRepository = bookListEntryRepository;

    this.bookListRepository = bookListRepository;
  }

  @Override
  public BookListEntry addBookToBookList(
      String bookListId, String bookId, String notes, boolean containSpoilers) {
    BookList bookList = bookListRepository.findById(new BookListId(bookListId)).orElseThrow(() -> {
      var message = String.format("Book List Not Found With ID '%s'", bookListId);

      throw new NotFoundException(message);
    });

    return null;
  }

  @Override
  public BookList createBookList(String user, String title, String description) {
    UserId userId = new UserId(user);

    BookList bookList = new BookList(userId, title, description);

    return null;
  }

  @Override
  public Paged<BookListEntry> query(String bookListId, Pagination pagination) {
    BookListId bookList = new BookListId(bookListId);

    return bookListEntryRepository.findByBookList(bookList, pagination);
  }

  @Override
  public Paged<BookList> query(Pagination pagination) {
    return bookListRepository.findAll(pagination);
  }
}
