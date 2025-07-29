package com.felipeassisdev.walletpp.service;

import com.felipeassisdev.walletpp.client.NotificationClient;
import com.felipeassisdev.walletpp.entity.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public boolean setNotification(Transfer transfer) {
        var response = notificationClient.sendNotification(transfer);

        if (response.getStatusCode().isError() || response.getBody() == null) {
            logger.error("Error while sending notification");
            return false;
        }

        return response.getBody().success();
    }
}
