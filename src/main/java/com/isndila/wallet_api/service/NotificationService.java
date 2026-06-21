package com.isndila.wallet_api.service;

import com.isndila.wallet_api.entity.Notification;
import com.isndila.wallet_api.entity.User;
import com.isndila.wallet_api.repo.NotificationRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepo notificationRepo;

    public NotificationService(NotificationRepo notificationRepo){
        this.notificationRepo = notificationRepo;
    }

    public List<Notification> getNotificationsForUser(User user){
        return notificationRepo.findByUserOrUserIsNullOrderByTimestampDesc(user);
    }

    public Notification creationNotification(User user, String title, String message, String category){
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setCategory(category);
        notification.setTimestamp(LocalDateTime.now());

        return notificationRepo.save(notification);
    }
}
