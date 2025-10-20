package com.bookmark.interfaces.controllers;

import com.bookmark.application.BookService;
import com.bookmark.interfaces.mappers.BookMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/books")
@RestController
public class BookController {
  private final BookMapper mapper;

  private final BookService service;

  public BookController(BookMapper mapper, BookService service) {
    this.mapper = mapper;

    this.service = service;
  }
}
