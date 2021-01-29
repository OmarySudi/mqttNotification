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
        this.pnConfiguration.setUuid("SUDI");

        this.pubnub = new PubNub(this.pnConfiguration);

    }

    public String sendNotification(Notification notification){

        final JsonObject messageJsonObject = new JsonObject();

        final JsonObject metaJsonObject = new JsonObject();
        
        metaJsonObject.addProperty("uuid","SUDI OMARY");

        messageJsonObject.addProperty("entry", "Earth");
        messageJsonObject.addProperty("update", "Hello sudi");


        pubnub.publish()
                .channel(notification.getTopic())
                 .message(notification.getNotification())
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
