package com.bookmark.common.domain;

public record Pagination(int page, int size, String sortBy, Sort direction) {
  public enum Sort { ASC, DESC }
}
