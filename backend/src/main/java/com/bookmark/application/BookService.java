package com.bookmark.application;

import com.bookmark.domain.Book;
import com.bookmark.infrastructure.persistence.JpaBookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  private final JpaBookRepository repository;

  public BookService(JpaBookRepository repository) {
    this.repository = repository;
  }

  public Book save(Book book) {
    String title = book.getTitle().toString();

    if (repository.findByTitle_Value(title).isPresent()) {
      String message = String.format("Book With Title '%s' Already Exists", title);

      throw new DuplicateEntryException(message);
    }

    return repository.save(book);
  }
}
