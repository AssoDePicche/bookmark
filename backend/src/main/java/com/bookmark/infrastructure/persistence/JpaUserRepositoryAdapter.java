package com.bookmark.infrastructure.persistence;

import com.bookmark.domain.Email;
import com.bookmark.domain.User;
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
    return repository.findByEmail_Value(email.toString());
  }

  @Override
  public Optional<User> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  public Optional<User> findByUsername(Username username) {
    return repository.findByUsername_Value(username.toString());
  }

  @Override
  public User save(User user) {
    return repository.save(user);
  }
}
