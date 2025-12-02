package com.bookmark.review_service.application;

public record ReviewRequest(String user, String book, int rating, String text) {}
