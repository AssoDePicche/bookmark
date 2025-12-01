package com.bookmark.book_list_service.application;

public record BookListEntryResponse(
    String book, String notes, boolean containSpoilers, String dateAdded) {}
