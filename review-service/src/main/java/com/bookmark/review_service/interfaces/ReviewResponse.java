package com.bookmark.review_service.interfaces;

public record ReviewResponse(String user, String book, int rating, String text, String dateAdded) {}
