package com.bookmark.infrastructure.persistence;

import com.bookmark.domain.Email;
import com.bookmark.domain.User;
import com.bookmark.domain.UserRepository;
import com.bookmark.domain.Username;
import com.bookmark.interfaces.mappers.UserMapper;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserRepositoryAdapter implements UserRepository {
  private final JpaUserRepository repository;

  private final UserMapper mapper;

  public JpaUserRepositoryAdapter(JpaUserRepository repository, UserMapper mapper) {
    this.repository = repository;

    this.mapper = mapper;
  }

  @Override
  public Optional<User> findByEmail(Email email) {
    return repository.findByEmail(email.toString()).map(mapper::map);
  }

  @Override
  public Optional<User> findById(Long id) {
    return repository.findById(id).map(mapper::map);
  }

  @Override
  public Optional<User> findByUsername(Username username) {
    return repository.findByUsername(username.toString()).map(mapper::map);
  }

  @Override
  public User save(User user) {
    JpaUserEntity entity = mapper.mapToJpa(user);

    return mapper.map(repository.save(entity));
  }
}
