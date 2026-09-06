package org.example.Notifications;

public class SmsServices implements NotificationsServices {

    @Override
    public void sendNotification() {
        System.out.println("Sms notification is send");
    }
}
