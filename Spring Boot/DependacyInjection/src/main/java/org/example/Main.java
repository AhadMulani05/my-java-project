package org.example;

import org.example.Notifications.EmailServices;
import org.example.Notifications.FakeServices;
import org.example.Notifications.NotificationsServices;
import org.example.Notifications.SmsServices;
import org.example.Notifications.PopUpServiecs;

public class Main {
    public static void main(String[] args) {

        NotificationsServices notification = new PopUpServiecs();

        OrderServices order = new OrderServices(notification);

        order.placeOrder();
    }
}