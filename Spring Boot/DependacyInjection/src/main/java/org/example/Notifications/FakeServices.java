package org.example.Notifications;

public class FakeServices implements NotificationsServices {

    @Override
    public void sendNotification() {
        System.out.println("fake notification is send");
    }
}
