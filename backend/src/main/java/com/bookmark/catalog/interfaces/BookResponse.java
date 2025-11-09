package com.bookmark.catalog.interfaces;

public record BookResponse(
    String isbn, String title, String description, String genre, String publicationDate) {}
