package com.bookmark.booklist.domain;

import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;
import com.bookmark.user.domain.UserId;
import java.util.List;
import java.util.Optional;

public interface BookListRepository {
  Paged<BookList> findAll(Pagination pagination);

  Optional<BookList> findById(BookListId id);

  Optional<BookList> findByTitle(Title title);

  Paged<BookList> findByUser(UserId user, Pagination pagination);

  BookList save(BookList bookList);
}
