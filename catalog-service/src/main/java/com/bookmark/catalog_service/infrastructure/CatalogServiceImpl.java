package com.bookmark.catalog.infrastructure;

import com.bookmark.catalog.application.BookService;
import com.bookmark.catalog.domain.Book;
import com.bookmark.catalog.domain.BookRepository;
import com.bookmark.catalog.domain.Title;
import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;
import com.bookmark.common.domain.exception.DuplicateEntryException;
import com.bookmark.common.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl implements BookService {
  private final BookRepository repository;

  public CatalogServiceImpl(BookRepository repository) {
    this.repository = repository;
  }

  @Override
  public Paged<Book> query(Pagination pagination) {
    return repository.findAll(pagination);
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
