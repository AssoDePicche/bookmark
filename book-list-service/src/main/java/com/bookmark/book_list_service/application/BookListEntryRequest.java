package com.bookmark.book_list_service.application;

public record BookListEntryRequest(
    String bookListId, String bookId, String notes, boolean containSpoilers) {}
