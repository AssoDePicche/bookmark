package com.bookmark.review_service.interfaces;

public record ReviewRequest(String user, String book, int rating, String text) {}
