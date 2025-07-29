package com.felipeassisdev.walletpp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAllowedException extends CustomException {

    private final String detail;

    public TransferNotAllowedException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Transfer not allowed");
        problemDetail.setDetail(detail);

        return problemDetail;
    }
}
