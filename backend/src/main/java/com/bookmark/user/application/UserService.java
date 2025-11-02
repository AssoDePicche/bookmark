package com.bookmark.user.application;

import com.bookmark.user.domain.Password;
import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserId;
import com.bookmark.user.domain.Username;

public interface UserService {
  User query(UserId id);

  User query(Username username);

  User save(User user);

  boolean matches(Password rawPassword, Password encrypted);
}
