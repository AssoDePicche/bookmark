package com.bookmark.catalog.application;

import java.util.List;

public record BookSearchResult(String title, String publishDate, String physicalFormat,
    List<String> publishers, List<String> isbn10, List<String> isbn13) {}
