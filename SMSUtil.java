package com.resq.utils;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;

public class SMSUtil {

    private static final String API_KEY = "2a2b9642";      // paste Vonage API Key
    private static final String API_SECRET = "f8qBkf5yvMn7VmGK"; // paste Vonage API Secret

    public static void sendSMS(String toNumber, String messageText) {
        try {
            VonageClient client = VonageClient.builder()
                    .apiKey(API_KEY)
                    .apiSecret(API_SECRET)
                    .build();

            TextMessage message = new TextMessage("ResQApp", toNumber, messageText);

            SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

            if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
                System.out.println("✅ SMS sent successfully to " + toNumber);
            } else {
                System.out.println("❌ SMS failed: " + response.getMessages().get(0).getErrorText());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Exception while sending SMS.");
        }
    }
}
