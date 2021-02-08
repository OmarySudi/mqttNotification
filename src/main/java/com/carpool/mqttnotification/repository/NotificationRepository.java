package com.carpool.mqttnotification.repository;

import com.carpool.mqttnotification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {

    @Query("SELECT n from Notification n where n.userID =:userID AND n.isRead=:isRead")
    List<Notification> findByUserIDAndIsRead(@Param("userID") String userID,@Param("isRead") boolean isRead);

    @Query("SELECT n from Notification n where n.role =:role AND n.isRead=:isRead")
    List<Notification> findByRoleAndIsRead(@Param("role") String role,@Param("isRead") boolean isRead);

    @Query("SELECT n from Notification n where n.userID =:userID ORDER BY id DESC")
    List<Notification> findByUserID(String userID);

    @Query("SELECT n from Notification n where n.role =:role ORDER BY id DESC")
    List<Notification> findByRole(String role);

}
