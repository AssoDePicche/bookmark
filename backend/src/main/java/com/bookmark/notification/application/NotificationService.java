package com.bookmark.notification.application;

import com.bookmark.notification.domain.Notification;

public interface NotificationService {
  void send(Notification notification);
}
