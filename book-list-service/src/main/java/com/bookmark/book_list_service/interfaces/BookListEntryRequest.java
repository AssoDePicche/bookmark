package com.bookmark.book_list_service.interfaces;

public record BookListEntryRequest(
    String bookListId, String bookId, String notes, boolean containSpoilers) {}
