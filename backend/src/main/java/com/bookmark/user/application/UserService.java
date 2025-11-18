package com.bookmark.user.application;

import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserId;
import java.util.Set;

public interface UserService {
  User create(String email, String username, String password, Set<String> roles);

  User currentUser(String username, String token);

  User query(UserId id);
}
