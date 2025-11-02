package com.bookmark.application;

import com.bookmark.domain.Password;
import com.bookmark.domain.User;
import com.bookmark.domain.UserId;
import com.bookmark.domain.Username;

public interface UserService {
  User query(UserId id);

  User query(Username username);

  User save(User user);

  boolean matches(Password rawPassword, Password encrypted);
}
