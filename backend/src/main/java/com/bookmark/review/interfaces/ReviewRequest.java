package com.bookmark.review.interfaces;

public record ReviewRequest(String user, String book, int rating, String text) {}
