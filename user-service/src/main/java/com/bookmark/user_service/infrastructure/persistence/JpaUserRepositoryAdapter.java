package com.bookmark.user_service.infrastructure.persistence;

import com.bookmark.user_service.domain.Email;
import com.bookmark.user_service.domain.User;
import com.bookmark.user_service.domain.UserId;
import com.bookmark.user_service.domain.UserRepository;
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
  public User save(User user) {
    JpaUserEntity entity = JpaUserEntityMapper.map(user);

    repository.save(entity);

    return JpaUserEntityMapper.map(entity);
  }
}
