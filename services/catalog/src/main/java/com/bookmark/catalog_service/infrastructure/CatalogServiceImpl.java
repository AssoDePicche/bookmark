package com.bookmark.catalog_service.infrastructure;

import com.bookmark.catalog_service.application.CatalogService;
import com.bookmark.catalog_service.domain.Book;
import com.bookmark.catalog_service.domain.BookRepository;
import com.bookmark.catalog_service.domain.Paged;
import com.bookmark.catalog_service.domain.Pagination;
import com.bookmark.catalog_service.domain.Title;
import com.bookmark.catalog_service.domain.exception.DuplicateEntryException;
import com.bookmark.catalog_service.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl implements CatalogService {
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
