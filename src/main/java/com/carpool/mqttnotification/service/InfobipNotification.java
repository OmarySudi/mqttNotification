package com.carpool.mqttnotification.service;

import com.carpool.mqttnotification.model.Message;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class InfobipNotification {

    @Value("${infobipKey}")
    private String apiKey;

    public String sendSMS(Message message){

        String stringResponse = "";
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(
                "{\"messages\": [{\"from\":\"InfoSMS\",\"destinations\":[{\"to\":\""+message.getRecipient()+"\"}],\"text\":\""+message.getText()+"\"}]}",mediaType
                    );

        Request request = new Request.Builder()
                .url("https://19gwwk.api.infobip.com/sms/2/text/advanced")
                .method("POST", body)
                .addHeader("Authorization", "App "+apiKey)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        try{
            Response response = client.newCall(request).execute();
            stringResponse = response.body().string();
        }catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        return stringResponse;

    }
}
