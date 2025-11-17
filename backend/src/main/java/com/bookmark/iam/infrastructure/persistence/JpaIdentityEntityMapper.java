package com.bookmark.iam.infrastructure.persistence;

import com.bookmark.iam.domain.Identity;

public class JpaIdentityEntityMapper {
  public static Identity map(JpaIdentityEntity entity) {
    return new Identity(
        entity.getId(), entity.getUsername(), entity.getPassword(), entity.getRoles());
  }

  public static JpaIdentityEntity map(Identity identity) {
    return new JpaIdentityEntity(identity.getId(), identity.getUsername().toString(),
        identity.getPassword().toString(), identity.getRoles());
  }
}
