package com.bookmark.interfaces.dto.request;

import java.time.LocalDate;

public record BookRequest(
    String title, String description, String genre, LocalDate publicationDate) {}
