package com.bookmark.user.infrastructure;

import com.bookmark.common.domain.exception.DuplicateEntryException;
import com.bookmark.common.domain.exception.NotFoundException;
import com.bookmark.user.application.UserService;
import com.bookmark.user.domain.Email;
import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserId;
import com.bookmark.user.domain.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public User query(UserId id) {
    return repository.findById(id).orElseThrow(() -> {
      var message = String.format("User Not Found With ID '%s'", id);

      throw new NotFoundException(message);
    });
  }

  @Override
  public User save(User user) {
    Email email = user.getEmail();

    if (repository.findByEmail(email).isPresent()) {
      var message = String.format("User With Email '%s' Already Exists", email);

      throw new DuplicateEntryException(message);
    }

    return repository.save(user);
  }
}
