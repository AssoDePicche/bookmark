package com.bookmark.notification_service.application;

import com.bookmark.notification_service.domain.Notification;

public interface NotificationService {
  void send(Notification notification);
}
