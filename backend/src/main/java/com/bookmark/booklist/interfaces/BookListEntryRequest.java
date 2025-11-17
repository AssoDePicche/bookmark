package com.bookmark.booklist.interfaces;

public record BookListEntryRequest(
    String bookListId, String bookId, String notes, boolean containSpoilers) {}
