package com.bookmark.catalog_service.interfaces;

public record BookResponse(
    String isbn, String title, String description, String genre, String publicationDate) {}
