package com.bookmark.iam_service.infrastructure.persistence;

import com.bookmark.iam_service.domain.Identity;
import com.bookmark.iam_service.domain.IdentityRepository;
import com.bookmark.iam_service.domain.Username;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class JpaIdentityRepositoryAdapter implements IdentityRepository {
  private final JpaIdentityRepository repository;

  public JpaIdentityRepositoryAdapter(JpaIdentityRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<Identity> findById(UUID id) {
    return repository.findById(id).map(JpaIdentityEntityMapper::map);
  }

  @Override
  public Optional<Identity> findByUsername(Username username) {
    return repository.findByUsername(username.toString()).map(JpaIdentityEntityMapper::map);
  }

  @Override
  public Identity save(Identity identity) {
    JpaIdentityEntity entity = JpaIdentityEntityMapper.map(identity);

    repository.save(entity);

    return JpaIdentityEntityMapper.map(entity);
  }
}
