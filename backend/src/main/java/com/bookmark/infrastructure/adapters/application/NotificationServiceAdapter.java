package com.bookmark.infrastructure.adapters.application;

import com.bookmark.application.NotificationService;
import com.bookmark.domain.Notification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceAdapter implements NotificationService {
  private final JavaMailSender sender;

  public NotificationServiceAdapter(JavaMailSender sender) {
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
