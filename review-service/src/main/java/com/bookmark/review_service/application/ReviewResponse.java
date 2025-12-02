package com.bookmark.review_service.application;

public record ReviewResponse(String user, String book, int rating, String text, String dateAdded) {}
