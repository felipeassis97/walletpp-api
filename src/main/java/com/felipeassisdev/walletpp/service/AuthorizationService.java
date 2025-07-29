package com.felipeassisdev.walletpp.service;

import com.felipeassisdev.walletpp.client.AuthorizationClient;
import com.felipeassisdev.walletpp.controller.dto.TransferDto;
import com.felipeassisdev.walletpp.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto dto) {
        var response = authorizationClient.isAuthorized(dto);
        if (response.getStatusCode().isError() || response.getBody() == null) {
            logger.error("Authorization service returned error or empty response");
            throw new CustomException();
        }
        
        return response.getBody().authorized();
    }
}
