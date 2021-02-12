package com.carpool.mqttnotification.service;

import com.carpool.mqttnotification.model.Notification;
import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import org.springframework.stereotype.Service;

@Service
public class PubNubNotification {

    final String channelName = "the_guide";

    private PNConfiguration pnConfiguration;

    private PubNub pubnub;

    PubNubNotification(){
        this.pnConfiguration = new PNConfiguration();
        this.pnConfiguration.setSubscribeKey("sub-c-31261c0a-2985-11eb-8221-521a7107d7f7");
        this.pnConfiguration.setPublishKey("pub-c-fa9ff42c-0e4d-495f-9b43-fd9c83283f6f");
        this.pnConfiguration.setUuid("AksonCabPubNubPublisher");

        this.pubnub = new PubNub(this.pnConfiguration);

    }

    public String sendDriverNotification(Notification notification){

        final JsonObject messageJsonObject = new JsonObject();

        final JsonObject metaJsonObject = new JsonObject();

        metaJsonObject.addProperty("routeID",notification.getEntity_id());

        messageJsonObject.addProperty("message", notification.getNotification());
        messageJsonObject.addProperty("tripID",notification.getUser_id());
        messageJsonObject.addProperty("customerID", notification.getEntityID());

        pubnub.publish()
                .channel(notification.getTopic())
                 .message(messageJsonObject)
                  .meta(metaJsonObject)
                  .async((result,publishStatus)->{
                      if(!publishStatus.isError())
                      {
                          System.out.println("message published");

                      }else{
                          System.out.println("message not published");
                      }
                  });

        return "Test message";
    }

    public String driverCancellTrip(Notification notification){

        final JsonObject messageJsonObject = new JsonObject();

        final JsonObject metaJsonObject = new JsonObject();

        metaJsonObject.addProperty("customerID",notification.getEntityID());

        messageJsonObject.addProperty("message", notification.getNotification());
        messageJsonObject.addProperty("driverID", notification.getUserID());
        messageJsonObject.addProperty("tripID",notification.getUser_id());
        messageJsonObject.addProperty("routeID",notification.getEntity_id());

        pubnub.publish()
                .channel(notification.getTopic())
                .message(messageJsonObject)
                .meta(metaJsonObject)
                .async((result,publishStatus)->{
                    if(!publishStatus.isError())
                    {
                        System.out.println("message published");

                    }else{
                        System.out.println("message not published");
                    }
                });

        return "Test message";
    }
}
