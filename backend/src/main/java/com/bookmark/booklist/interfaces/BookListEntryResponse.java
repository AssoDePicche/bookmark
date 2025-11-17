package com.bookmark.booklist.interfaces;

public record BookListEntryResponse(
    String book, String notes, boolean containSpoilers, String dateAdded) {}
