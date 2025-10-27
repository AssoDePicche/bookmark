package com.bookmark.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record Username(String value) {
  @Override
  public String toString() {
    return value;
  }
}
