package com.bookmark.infrastructure.adapters.persistence;

import com.bookmark.domain.Email;
import com.bookmark.domain.User;
import com.bookmark.domain.UserId;
import com.bookmark.domain.UserRepository;
import com.bookmark.domain.Username;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserRepositoryAdapter implements UserRepository {
  private final JpaUserRepository repository;

  public JpaUserRepositoryAdapter(JpaUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<User> findByEmail(Email email) {
    return repository.findByEmail(email.toString()).map(JpaUserEntityMapper::map);
  }

  @Override
  public Optional<User> findById(UserId id) {
    return repository.findById(id.value()).map(JpaUserEntityMapper::map);
  }

  @Override
  public Optional<User> findByUsername(Username username) {
    return repository.findByUsername(username.toString()).map(JpaUserEntityMapper::map);
  }

  @Override
  public User save(User user) {
    JpaUserEntity entity = JpaUserEntityMapper.map(user);

    repository.save(entity);

    return JpaUserEntityMapper.map(entity);
  }
}
