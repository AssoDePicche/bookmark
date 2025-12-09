package com.bookmark.iam_service.domain;

import java.util.List;

public record Paged<T>(
    List<T> content, int page, int size, long totalElements, int totalPages, boolean last) {}
