package com.felipeassisdev.walletpp.client;

import com.felipeassisdev.walletpp.client.dto.NotificationResponse;
import com.felipeassisdev.walletpp.entity.Transfer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notification",
        url = "${client.notification.service.url}")
public interface NotificationClient {
    @PostMapping
    ResponseEntity<NotificationResponse> sendNotification(@RequestBody Transfer transfer);
}
