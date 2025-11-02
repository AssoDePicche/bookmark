package com.bookmark.user.application;

import com.bookmark.user.domain.Password;
import com.bookmark.user.domain.Username;

public interface AuthenticationService {
  String authenticate(Username username, Password password);
}
