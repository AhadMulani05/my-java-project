package com.example.ProfileDemoApplication;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class NotificationServiceImpl implements NotificationService{


    @Override
    public String send() {
        return "Here is a notification";
    }
}
