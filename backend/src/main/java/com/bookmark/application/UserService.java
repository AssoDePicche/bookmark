package com.bookmark.application;

import com.bookmark.domain.Email;
import com.bookmark.domain.Password;
import com.bookmark.domain.User;
import com.bookmark.domain.UserId;
import com.bookmark.domain.UserRepository;
import com.bookmark.domain.Username;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final PasswordEncoder encoder;

  private final UserRepository repository;

  public UserService(PasswordEncoder encoder, UserRepository repository) {
    this.encoder = encoder;

    this.repository = repository;
  }

  public User query(UserId id) {
    return repository.findById(id).orElseThrow(() -> {
      var message = String.format("User Not Found With ID '%s'", id);

      throw new NotFoundException(message);
    });
  }

  public User query(Username username) {
    return repository.findByUsername(username).orElseThrow(() -> {
      var message = String.format("User Not Found With Username '%s'", username);

      throw new NotFoundException(message);
    });
  }

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

  public boolean matches(Password rawPassword, Password encrypted) {
    return encoder.matches(rawPassword.toString(), encrypted.toString());
  }
}
