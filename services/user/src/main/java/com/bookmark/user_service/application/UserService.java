package com.bookmark.user_service.application;

import com.bookmark.user_service.domain.User;
import com.bookmark.user_service.domain.UserId;
import java.util.Set;

public interface UserService {
  User create(String email, String username, String password, Set<String> roles);

  User currentUser(String username, String token);

  User query(UserId id);
}
