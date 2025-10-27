package com.bookmark.interfaces.dto.book;

public record BookResponse(
    String isbn, String title, String description, String genre, String publicationDate) {}
