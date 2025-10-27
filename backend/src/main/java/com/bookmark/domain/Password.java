package com.bookmark.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record Password(String value) {
  @Override
  public String toString() {
    return value;
  }
}
