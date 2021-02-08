package com.carpool.mqttnotification.model;

import javax.persistence.*;


@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="notification")
    private String notification;

    @Column(name="userID")
    private String userID;

    @Column(name="user_id")
    private String user_id;

    @Column(name="is_read")
    private boolean isRead;

    @Column(name="topic")
    private String topic;

    @Column(name="role")
    private String role;

    @Column(name="icons")
    private String icons;

    @Column(name="entintyID")
    private String entintyID;

    @Column(name="entinty_id")
    private String entinty_id;

    @Column(name="category")
    private String category;


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public String getEntintyID() {
        return entintyID;
    }

    public void setEntintyID(String entintyID) {
        this.entintyID = entintyID;
    }

    public String getEntinty_id() {
        return entinty_id;
    }

    public void setEntinty_id(String entinty_id) {
        this.entinty_id = entinty_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
