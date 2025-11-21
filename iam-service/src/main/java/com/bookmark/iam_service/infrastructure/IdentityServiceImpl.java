package com.bookmark.iam_service.infrastructure;

import com.bookmark.iam_service.application.IdentityService;
import com.bookmark.iam_service.domain.Identity;
import com.bookmark.iam_service.domain.IdentityRepository;
import com.bookmark.iam_service.domain.Password;
import com.bookmark.iam_service.domain.Username;
import com.bookmark.iam_service.domain.exception.DuplicateEntryException;
import com.bookmark.iam_service.domain.exception.NotFoundException;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class IdentityServiceImpl implements IdentityService {
  private final AuthenticationCoordinator coordinator;

  private final IdentityRepository repository;

  private final JsonWebTokenService service;

  public IdentityServiceImpl(AuthenticationCoordinator coordinator, IdentityRepository repository,
      JsonWebTokenService service) {
    this.coordinator = coordinator;

    this.repository = repository;

    this.service = service;
  }

  @Override
  public String authenticate(String username, String password) {
    Identity identity = query(username);

    coordinator.verifyCredentials(password, identity);

    return service.generateToken(identity);
  }

  @Override
  public Identity createIdentity(String username, String password, Set<String> roles) {
    var identity = new Identity(username, coordinator.encode(password), roles);

    if (repository.findByUsername(new Username(username)).isPresent()) {
      var message = String.format("User With Username '%s' Already Exists", username);

      throw new DuplicateEntryException(message);
    }

    repository.save(identity);

    return identity;
  }

  @Override
  public Identity query(String username) {
    return repository.findByUsername(new Username(username)).orElseThrow(() -> {
      var message = String.format("Identity Not Found With Username '%s'", username);

      return new NotFoundException(message);
    });
  }

  @Override
  public Identity query(UUID id) {
    return repository.findById(id).orElseThrow(() -> {
      var message = String.format("Identity Not Found With ID '%s'", id);

      return new NotFoundException(message);
    });
  }
}
