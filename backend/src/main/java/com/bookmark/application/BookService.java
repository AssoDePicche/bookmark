package com.bookmark.application;

import com.bookmark.domain.Book;
import com.bookmark.domain.BookRepository;
import com.bookmark.domain.Title;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  private final BookRepository repository;

  public BookService(BookRepository repository) {
    this.repository = repository;
  }

  public Book save(Book book) {
    Title title = book.getTitle();

    if (repository.findByTitle(title).isPresent()) {
      String message = String.format("Book With Title '%s' Already Exists", title);

      throw new DuplicateEntryException(message);
    }

    return repository.save(book);
  }
}
