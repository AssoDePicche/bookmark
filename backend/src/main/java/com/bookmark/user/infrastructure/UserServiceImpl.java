package com.bookmark.user.infrastructure;

import com.bookmark.common.domain.exception.DuplicateEntryException;
import com.bookmark.common.domain.exception.NotFoundException;
import com.bookmark.user.application.IAMService;
import com.bookmark.user.application.UserService;
import com.bookmark.user.domain.Email;
import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserId;
import com.bookmark.user.domain.UserRepository;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final IAMService iam;

  private final UserRepository repository;

  public UserServiceImpl(IAMService iam, UserRepository repository) {
    this.iam = iam;

    this.repository = repository;
  }

  @Override
  public User create(String email, String username, String password, Set<String> roles) {
    IAMService.RegistrationResponse identity = iam.register(username, password, roles);

    var id = identity.id().toString();

    var user = new User(id, email, roles);

    if (repository.findByEmail(user.getEmail()).isPresent()) {
      var message = String.format("User With Email '%s' Already Exists", email);

      throw new DuplicateEntryException(message);
    }

    return repository.save(user);
  }

  @Override
  public User currentUser(String username, String token) {
    IAMService.IdentityResponse identity = iam.retrieveIdentity(username, token);

    var id = new UserId(identity.id());

    return query(id);
  }

  @Override
  public User query(UserId id) {
    return repository.findById(id).orElseThrow(() -> {
      var message = String.format("User Not Found With ID '%s'", id);

      throw new NotFoundException(message);
    });
  }
}
