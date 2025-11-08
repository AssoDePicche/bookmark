package com.bookmark.booklist.domain;

import com.bookmark.user.domain.UserId;
import java.util.List;
import java.util.Optional;

public interface BookListRepository {
  Optional<BookList> findById(BookListId id);

  List<BookList> findByUser(UserId user);

  BookList save(BookList list);
}
