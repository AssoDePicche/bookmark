package com.bookmark.catalog.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record OpenLibraryBookSearchResult(String title,
    @JsonProperty("publish_date") String publishDate,
    @JsonProperty("physical_format") String physicalFormat, List<String> publishers,
    @JsonProperty("isbn_10") List<String> isbn10, @JsonProperty("isbn_13") List<String> isbn13) {}
