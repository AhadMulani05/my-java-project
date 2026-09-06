package org.example;

import org.example.Notifications.EmailServices;
import org.example.Notifications.FakeServices;
import org.example.Notifications.NotificationsServices;
import org.example.Notifications.SmsServices;

public class OrderServices {

    NotificationsServices notification;
    OrderServices(NotificationsServices notification) {
        this.notification = notification;
    }

    public void placeOrder() {
        notification.sendNotification();
        System.out.println("Order is received");
    }
}
