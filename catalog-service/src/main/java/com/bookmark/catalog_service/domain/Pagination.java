package com.bookmark.catalog_service.domain;

public record Pagination(int page, int size, String sortBy, Sort direction) {
  public enum Sort { ASC, DESC }
}
