package com.bookmark.notification_service.infrastructure;

import com.bookmark.notification_service.application.NotificationService;
import com.bookmark.notification_service.domain.Notification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
  private final JavaMailSender sender;

  public NotificationServiceImpl(JavaMailSender sender) {
    this.sender = sender;
  }

  @Override
  public void send(Notification notification) {
    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(notification.recipient());

    message.setSubject(notification.subject());

    message.setText(notification.message());

    sender.send(message);
  }
}
