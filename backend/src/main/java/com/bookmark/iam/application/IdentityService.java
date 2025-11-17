package com.bookmark.iam.application;

import com.bookmark.iam.domain.Identity;
import java.util.Set;
import java.util.UUID;

public interface IdentityService {
  String authenticate(String username, String password);

  Identity createIdentity(String username, String password, Set<String> roles);

  Identity query(String username);

  Identity query(UUID id);
}
