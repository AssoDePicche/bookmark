package com.bookmark.user.application;

import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserId;

public interface UserService {
  User query(UserId id);

  User save(User user);
}
