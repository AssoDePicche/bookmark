package com.bookmark.common.interfaces;

import java.util.List;

public record Paged<T>(
    List<T> content, int page, int size, long totalElements, int totalPages, boolean last) {}
