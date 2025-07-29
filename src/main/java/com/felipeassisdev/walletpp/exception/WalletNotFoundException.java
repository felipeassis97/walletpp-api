package com.felipeassisdev.walletpp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.util.UUID;

public class WalletNotFoundException extends CustomException {

    private final UUID walletID;

    public WalletNotFoundException(UUID walletID) {
        this.walletID = walletID;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Wallet not found");
        problemDetail.setDetail("There is no wallet with that ID " + walletID);

        return problemDetail;
    }
}
