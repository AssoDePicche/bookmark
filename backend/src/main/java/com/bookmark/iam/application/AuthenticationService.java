package com.bookmark.iam.application;

public interface AuthenticationService {
  String authenticate(String username, String password);
}
