package com.bookmark.interfaces.dto.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record BookRequest(@NotEmpty(message = "You must inform an ISBN") String isbn,
    @NotEmpty(message = "You must inform a title") String title,
    @NotEmpty(message = "You must inform a description") String description,
    @NotEmpty(message = "You must inform a genre") String genre,
    @NotNull(message = "You must inform a publication date") LocalDate publicationDate) {}
