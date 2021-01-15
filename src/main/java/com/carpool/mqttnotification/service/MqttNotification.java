package com.carpool.mqttnotification.service;

import com.carpool.mqttnotification.model.Notification;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class MqttNotification implements MqttCallback {

    private final int qos = 1;
    private MqttClient client;

    public MqttNotification() throws MqttException {

        String username = "gfuxuwzr";
        String password = "Z4WwbATYPT6U";
        String clientId = "AksonCabCarPool";

        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);
        conOpt.setUserName(username);
        conOpt.setPassword(password.toCharArray());

        this.client = new MqttClient("tcp://tailor.cloudmqtt.com:11621", clientId, new MemoryPersistence());
        this.client.setCallback(this);
        this.client.connect(conOpt);

    }

    public void sendNotification(Notification notification){
        try{
            MqttMessage message = new MqttMessage(notification.getNotification().getBytes());
            message.setQos(qos);
            System.out.println("Topic is "+notification.getTopic());

            this.client.publish(notification.getTopic(), message); // Blocking publish

        }
        catch(MqttException ex){

        }
    }

    /**
     * @see MqttCallback#connectionLost(Throwable)
     */
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost because: " + cause);
        System.exit(1);
    }

    /**
     * @see MqttCallback#deliveryComplete(IMqttDeliveryToken)
     */
    public void deliveryComplete(IMqttDeliveryToken token) {
    }

    /**
     * @see MqttCallback#messageArrived(String, MqttMessage)
     */
    public void messageArrived(String topic, MqttMessage message) throws MqttException {
        System.out.println(String.format("[%s] %s", topic, new String(message.getPayload())));
    }
}
