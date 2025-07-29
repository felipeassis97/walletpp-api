package com.felipeassisdev.walletpp.service;

import com.felipeassisdev.walletpp.controller.dto.TransferDto;
import com.felipeassisdev.walletpp.entity.Transfer;
import com.felipeassisdev.walletpp.entity.Wallet;
import com.felipeassisdev.walletpp.exception.TransferNotAllowedException;
import com.felipeassisdev.walletpp.exception.WalletNotFoundException;
import com.felipeassisdev.walletpp.repository.TransferRepository;
import com.felipeassisdev.walletpp.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final WalletRepository walletRepository;
    private final TransferRepository transferRepository;
    private final NotificationService notificationService;
    private final AuthorizationService authorizationService;

    public TransferService(TransferRepository transferRepository,
                           WalletRepository walletRepository,
                           AuthorizationService authorizationService,
                           NotificationService notificationService) {
        this.walletRepository = walletRepository;
        this.transferRepository = transferRepository;
        this.notificationService = notificationService;
        this.authorizationService = authorizationService;
    }


    @Transactional
    public Transfer transfer(TransferDto dto) {
        var sender = walletRepository.findById(dto.payer()).orElseThrow(
                () -> new WalletNotFoundException(dto.payer())
        );

        var receiver = walletRepository.findById(dto.payee()).orElseThrow(
                () -> new WalletNotFoundException(dto.payee())
        );

        validateTransfer(dto, sender);

        sender.debit(dto.amount());
        receiver.credit(dto.amount());

        var transfer = new Transfer(sender, receiver, dto.amount());
        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.setNotification(transferResult));
        return transferResult;
    }

    private void validateTransfer(TransferDto dto, Wallet sender) {
        if (!sender.isTransferAllowed()) {
            throw new TransferNotAllowedException("Operation not allowed for merchant users");
        }

        if (!sender.isBalanceEqualOrGreaterThan(dto.amount())) {
            throw new TransferNotAllowedException("Insufficient balance for this transfer");
        }

        if (!authorizationService.isAuthorized(dto)) {
            throw new TransferNotAllowedException("Authorization service not authorized this transfer");
        }
    }
}
