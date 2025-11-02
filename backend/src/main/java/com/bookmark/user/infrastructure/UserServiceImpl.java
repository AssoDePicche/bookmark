package com.bookmark.user.infrastructure;

import com.bookmark.common.domain.exception.DuplicateEntryException;
import com.bookmark.common.domain.exception.NotFoundException;
import com.bookmark.user.application.UserService;
import com.bookmark.user.domain.Email;
import com.bookmark.user.domain.Password;
import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserId;
import com.bookmark.user.domain.UserRepository;
import com.bookmark.user.domain.Username;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final PasswordEncoder encoder;

  private final UserRepository repository;

  public UserServiceImpl(PasswordEncoder encoder, UserRepository repository) {
    this.encoder = encoder;

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
  public User query(Username username) {
    return repository.findByUsername(username).orElseThrow(() -> {
      var message = String.format("User Not Found With Username '%s'", username);

      throw new NotFoundException(message);
    });
  }

  @Override
  public User save(User user) {
    Username username = user.getUsername();

    if (repository.findByUsername(username).isPresent()) {
      String message = String.format("User With Username '%s' Already Exists", username);

      throw new DuplicateEntryException(message);
    }

    Email email = user.getEmail();

    if (repository.findByEmail(email).isPresent()) {
      String message = String.format("User With Email '%s' Already Exists", email);

      throw new DuplicateEntryException(message);
    }

    String encodedPassword = encoder.encode(user.getPassword().toString());

    user.setPassword(encodedPassword);

    return repository.save(user);
  }

  @Override
  public boolean matches(Password rawPassword, Password encrypted) {
    return encoder.matches(rawPassword.toString(), encrypted.toString());
  }
}
