package org.example.Notifications;

public class EmailServices implements NotificationsServices {

    @Override
    public void sendNotification() {
        System.out.println("Email notification is send");
    }
}
