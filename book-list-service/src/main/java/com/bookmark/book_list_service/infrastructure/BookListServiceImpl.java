package com.bookmark.book_list_service.infrastructure;

import com.bookmark.book_list_service.application.BookListService;
import com.bookmark.book_list_service.domain.BookId;
import com.bookmark.book_list_service.domain.BookList;
import com.bookmark.book_list_service.domain.BookListEntry;
import com.bookmark.book_list_service.domain.BookListEntryRepository;
import com.bookmark.book_list_service.domain.BookListId;
import com.bookmark.book_list_service.domain.BookListRepository;
import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.domain.Pagination;
import com.bookmark.book_list_service.domain.UserId;
import com.bookmark.book_list_service.domain.exception.DuplicateEntryException;
import com.bookmark.book_list_service.domain.exception.NotFoundException;
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
