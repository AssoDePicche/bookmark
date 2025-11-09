package com.bookmark.iam.domain;

import java.util.Set;

public record Details(String id, String username, String password, Set<String> roles) {}
