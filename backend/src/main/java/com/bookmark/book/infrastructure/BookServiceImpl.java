package com.bookmark.book.infrastructure;

import com.bookmark.book.application.BookService;
import com.bookmark.book.domain.Book;
import com.bookmark.book.domain.BookRepository;
import com.bookmark.book.domain.Title;
import com.bookmark.common.domain.exception.DuplicateEntryException;
import com.bookmark.common.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
  private final BookRepository repository;

  public BookServiceImpl(BookRepository repository) {
    this.repository = repository;
  }

  @Override
  public Book save(Book book) {
    Title title = book.getTitle();

    if (repository.findByTitle(title).isPresent()) {
      String message = String.format("Book With Title '%s' Already Exists", title);

      throw new DuplicateEntryException(message);
    }

    return repository.save(book);
  }
}
