package com.felipeassisdev.walletpp.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferDto(
        @NotNull @DecimalMin("0.01") BigDecimal amount,
        @NotNull UUID payer,
        @NotNull UUID payee) {
}
