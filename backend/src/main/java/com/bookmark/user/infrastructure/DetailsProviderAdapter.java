package com.bookmark.user.infrastructure;

import com.bookmark.iam.domain.Details;
import com.bookmark.iam.domain.DetailsProvider;
import com.bookmark.user.application.UserService;
import com.bookmark.user.domain.User;
import com.bookmark.user.domain.UserId;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class DetailsProviderAdapter implements DetailsProvider {
  private final UserService service;

  public DetailsProviderAdapter(UserService service) {
    this.service = service;
  }

  @Override
  public Details findById(String id) {
    User user = service.query(new UserId(id));

    return new Details(user.getId().toString(), user.getUsername().toString(),
        user.getPassword().toString(), Set.of(user.getRole().toString()));
  }
}
