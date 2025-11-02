package com.bookmark.infrastructure.adapters.application;

import com.bookmark.application.BookService;
import com.bookmark.application.DuplicateEntryException;
import com.bookmark.application.NotFoundException;
import com.bookmark.domain.Book;
import com.bookmark.domain.BookRepository;
import com.bookmark.domain.Title;
import org.springframework.stereotype.Service;

@Service
public class BookServiceAdapter implements BookService {
  private final BookRepository repository;

  public BookServiceAdapter(BookRepository repository) {
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
