package com.carpool.mqttnotification.controller;

import com.carpool.mqttnotification.model.Notification;
import com.carpool.mqttnotification.repository.NotificationRepository;
import com.carpool.mqttnotification.response.CustomResponse;
import com.carpool.mqttnotification.service.MqttNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    MqttNotification mqttNotification;

    @PostMapping(value = "/send-notification")
    public ResponseEntity<CustomResponse<Notification>> sendNotification(@RequestBody Notification notification){
        Notification newNotification = new Notification();

        newNotification.setNotification(notification.getNotification());
        newNotification.setCategory(notification.getCategory());
        newNotification.setIcons(notification.getIcons());
        newNotification.setEntintyID(notification.getEntintyID());
        newNotification.setEntinty_id(notification.getEntinty_id());
        newNotification.setTopic(notification.getTopic());
        newNotification.setRole(notification.getRole());
        newNotification.setUser_id(notification.getUser_id());
        newNotification.setUserID(notification.getUserID());

        System.out.println("Before saving............");

        notificationRepository.save(newNotification);
        System.out.println("Before sending............");
        mqttNotification.sendNotification(newNotification);
        System.out.println("Notification sent............");

        CustomResponse<Notification> response = new CustomResponse<>();
        List notifications = new ArrayList();
        notifications.add(newNotification);
        response.setObjects(notifications);
        response.setMessage("Notification have been created successfully");
        response.setDetails("Operation successfully");
        response.setObjects(notifications);

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PostMapping(value = "/mark-read/{id}")
    public ResponseEntity<CustomResponse> markNotificationAsRead(@PathVariable(value = "id") Integer id){
        Optional<Notification> optional = notificationRepository.findById(id);
        if(optional.isPresent()){
            Notification notification = optional.get();
            notification.setIsRead(true);
            notificationRepository.save(notification);

            CustomResponse<Notification> response = new CustomResponse<>();
            List notifications = new ArrayList();
            notifications.add(notification);
            response.setObjects(notifications);
            response.setMessage("Notification have been read successfully");
            response.setDetails("Operation successfully");
            response.setObjects(notifications);

            return new ResponseEntity<>(response,HttpStatus.OK);


        }else{
                CustomResponse<Notification> response = new CustomResponse<>();
                response.setMessage("RECORD NOT FOUND");
                response.setDetails("There is no notification with specied id in the database");
                ArrayList errors = new ArrayList();
                errors.add("Notification not found");
                response.setErrors(errors);

                return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @GetMapping(value="/unread/{userID}")
    public ResponseEntity<CustomResponse<Notification>> getUnreadNotifications(@PathVariable(value="userID") String userID){
        List<Notification> notifications = notificationRepository.findByUserIDAndIsRead(userID,false);

        if(notifications.size() > 0){

            CustomResponse<Notification> response = new CustomResponse<>();

            response.setObjects(notifications);
            response.setMessage("Operation successfully");
            response.setObjects(notifications);
            return new ResponseEntity<>(response,HttpStatus.OK);

        }else {

            CustomResponse<Notification> response = new CustomResponse<>();
            response.setMessage("RECORD NOT FOUND");
            response.setDetails("There are no unread notifications of this user");
            ArrayList errors = new ArrayList();
            errors.add("Notifications not found");
            response.setErrors(errors);

            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }


    @GetMapping(value = "/all/{userID}")
    public ResponseEntity<CustomResponse<Notification>> getAllNotifications(@PathVariable(value = "userID") String userID){
        List<Notification> notifications = notificationRepository.findByUserID(userID);

        if(notifications.size() > 0){

            CustomResponse<Notification> response = new CustomResponse<>();

            response.setObjects(notifications);
            response.setMessage("Operation successfully");
            response.setObjects(notifications);
            return new ResponseEntity<>(response,HttpStatus.OK);

        }else {

            CustomResponse<Notification> response = new CustomResponse<>();
            response.setMessage("RECORD NOT FOUND");
            response.setDetails("There are no notifications for this user");
            ArrayList errors = new ArrayList();
            errors.add("Notifications not found");
            response.setErrors(errors);

            return new ResponseEntity<>(response,HttpStatus.OK);
        }

    }


    @GetMapping(value="/unread-byrole/{role}")
    public ResponseEntity<CustomResponse<Notification>> getUnreadNotificationsByRole(@PathVariable(value="role") String role){
        List<Notification> notifications = notificationRepository.findByRoleAndIsRead(role,false);

        if(notifications.size() > 0){

            CustomResponse<Notification> response = new CustomResponse<>();

            response.setObjects(notifications);
            response.setMessage("Operation successfully");
            response.setObjects(notifications);
            return new ResponseEntity<>(response,HttpStatus.OK);

        }else {

            CustomResponse<Notification> response = new CustomResponse<>();
            response.setMessage("RECORD NOT FOUND");
            response.setDetails("There are no unread notifications of this role");
            ArrayList errors = new ArrayList();
            errors.add("Notifications not found");
            response.setErrors(errors);

            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }


    @GetMapping(value = "/all-byrole/{role}")
    public ResponseEntity<CustomResponse<Notification>> getAllNotificationsByRole(@PathVariable(value = "role") String role){
        List<Notification> notifications = notificationRepository.findByRole(role);

        if(notifications.size() > 0){

            CustomResponse<Notification> response = new CustomResponse<>();

            response.setObjects(notifications);
            response.setMessage("Operation successfully");
            response.setObjects(notifications);
            return new ResponseEntity<>(response,HttpStatus.OK);

        }else {

            CustomResponse<Notification> response = new CustomResponse<>();
            response.setMessage("RECORD NOT FOUND");
            response.setDetails("There are no notifications for this role");
            ArrayList errors = new ArrayList();
            errors.add("Notifications not found");
            response.setErrors(errors);

            return new ResponseEntity<>(response,HttpStatus.OK);
        }

    }

}
