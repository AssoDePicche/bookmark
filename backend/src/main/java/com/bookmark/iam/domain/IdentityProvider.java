package com.bookmark.iam.domain;

public interface IdentityProvider {
  Identity findByUsername(String username);
}
