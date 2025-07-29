package com.felipeassisdev.walletpp.client;

import com.felipeassisdev.walletpp.client.dto.AuthorizationResponse;
import com.felipeassisdev.walletpp.controller.dto.TransferDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "authorization",
        url = "${client.authorization.service.url}")
public interface AuthorizationClient {
    @GetMapping
    ResponseEntity<AuthorizationResponse> isAuthorized(TransferDto dto);
}
