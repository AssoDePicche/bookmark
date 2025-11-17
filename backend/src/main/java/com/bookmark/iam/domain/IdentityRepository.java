package com.bookmark.iam.domain;

import com.bookmark.iam.domain.Username;
import java.util.Optional;
import java.util.UUID;

public interface IdentityRepository {
  Optional<Identity> findById(UUID id);

  Optional<Identity> findByUsername(Username username);

  Identity save(Identity identity);
}
