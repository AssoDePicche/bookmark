package com.bookmark.book_list_service.interfaces;

public record BookListEntryResponse(
    String book, String notes, boolean containSpoilers, String dateAdded) {}
