package com.bookmark.iam_service.domain;

import java.util.Optional;
import java.util.UUID;

public interface IdentityRepository {
  Optional<Identity> findById(UUID id);

  Optional<Identity> findByUsername(Username username);

  Identity save(Identity identity);
}
