package com.bookmark.application;

import com.bookmark.domain.Notification;

public interface NotificationService {
  void send(Notification notification);
}
