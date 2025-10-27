package com.bookmark.application;

import com.bookmark.domain.Password;
import com.bookmark.domain.User;
import com.bookmark.domain.Username;
import com.bookmark.infrastructure.persistence.JpaUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final PasswordEncoder encoder;

  private final JpaUserRepository repository;

  public UserService(PasswordEncoder encoder, JpaUserRepository repository) {
    this.encoder = encoder;

    this.repository = repository;
  }

  public User query(Long id) {
    return repository.findById(id).orElseThrow(() -> {
      var message = String.format("User Not Found With ID '%d'", id);

      throw new NotFoundException(message);
    });
  }

  public User query(Username username) {
    String value = username.toString();

    return repository.findByUsername_Value(value).orElseThrow(() -> {
      var message = String.format("User Not Found With Username '%s'", value);

      throw new NotFoundException(message);
    });
  }

  public User save(User user) {
    String username = user.getUsername().toString();

    if (repository.findByUsername_Value(username).isPresent()) {
      String message = String.format("User With Username '%s' Already Exists", username);

      throw new DuplicateEntryException(message);
    }

    String email = user.getEmail().toString();

    if (repository.findByEmail_Value(email).isPresent()) {
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
