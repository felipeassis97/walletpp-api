package com.felipeassisdev.walletpp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletAlreadyExistsException extends CustomException {
    private final String detail;

    public WalletAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Wallet data already exists");
        problemDetail.setDetail(detail);

        return problemDetail;
    }
}
