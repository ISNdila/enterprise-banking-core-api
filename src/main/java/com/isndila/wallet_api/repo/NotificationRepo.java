package com.isndila.wallet_api.repo;

import com.isndila.wallet_api.entity.Notification;
import com.isndila.wallet_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
    List<Notification> findByUserOrUserIsNullOrderByTimestampDesc(User user);
}
