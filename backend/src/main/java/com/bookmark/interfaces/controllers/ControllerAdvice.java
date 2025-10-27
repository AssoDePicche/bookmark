package com.bookmark.interfaces.controllers;

import com.bookmark.application.DuplicateEntryException;
import com.bookmark.application.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ControllerAdvice {
  @ExceptionHandler({
      HttpMessageNotReadableException.class,
      IllegalArgumentException.class,
      MethodArgumentNotValidException.class,
      ValidationException.class,
  })
  public ResponseEntity<Response>
  badRequest(HttpServletRequest request, Exception exception) {
    return from(request, exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({
      DuplicateEntryException.class,
  })
  public ResponseEntity<Response>
  conflict(HttpServletRequest request, Exception exception) {
    return from(request, exception, HttpStatus.CONFLICT);
  }

  @ExceptionHandler({
      Exception.class,
  })
  public ResponseEntity<Response>
  internalServerError(HttpServletRequest request, Exception exception) {
    return from(request, exception, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({
      NoHandlerFoundException.class,
      NotFoundException.class,
  })
  public ResponseEntity<Response>
  notFound(HttpServletRequest request, Exception exception) {
    return from(request, exception, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({
      SecurityException.class,
  })
  public ResponseEntity<Response>
  unauthorized(HttpServletRequest request, Exception exception) {
    return from(request, exception, HttpStatus.UNAUTHORIZED);
  }

  private ResponseEntity<Response> from(
      HttpServletRequest request, Exception exception, HttpStatus status) {
    var response = new Response.Builder()
                       .status(status.value())
                       .message(exception.getMessage())
                       .path(request.getRequestURI().toString())
                       .build();

    return ResponseEntity.status(status).body(response);
  }
}

record Response(String timestamp, int status, String message, String path) {
  public static class Builder {
    private int status;

    private String message;

    private String path;

    public Builder status(int status) {
      this.status = status;

      return this;
    }

    public Builder message(String message) {
      this.message = message;

      return this;
    }

    public Builder path(String path) {
      this.path = path;

      return this;
    }

    public Response build() {
      String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

      return new Response(timestamp, status, message, path);
    }
  }
}
