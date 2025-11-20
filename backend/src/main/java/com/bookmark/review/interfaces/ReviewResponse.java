package com.bookmark.review.interfaces;

public record ReviewResponse(String user, String book, int rating, String text, String dateAdded) {}
