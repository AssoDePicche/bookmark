package com.bookmark.book_list_service.domain;

import com.bookmark.book_list_service.domain.Paged;
import com.bookmark.book_list_service.domain.Pagination;
import java.util.List;
import java.util.Optional;

public interface BookListRepository {
  Paged<BookList> findAll(Pagination pagination);

  Optional<BookList> findById(BookListId id);

  Optional<BookList> findByTitle(Title title);

  Paged<BookList> findByUser(UserId user, Pagination pagination);

  BookList save(BookList bookList);
}
