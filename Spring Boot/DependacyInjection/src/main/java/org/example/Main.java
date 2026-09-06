package org.example;

import org.example.Notifications.EmailServices;
import org.example.Notifications.FakeServices;
import org.example.Notifications.NotificationsServices;
import org.example.Notifications.SmsServices;

public class Main {
    public static void main(String[] args) {

        NotificationsServices notification = new FakeServices();

        OrderServices order = new OrderServices(notification);

        order.placeOrder();
    }
}