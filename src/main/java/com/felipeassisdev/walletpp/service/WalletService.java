package com.felipeassisdev.walletpp.service;

import com.felipeassisdev.walletpp.entity.Wallet;
import com.felipeassisdev.walletpp.repository.WalletRepository;
import com.felipeassisdev.walletpp.controller.dto.CreateWalletDto;
import com.felipeassisdev.walletpp.exception.WalletAlreadyExistsException;

import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {
        var currentWallet = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (currentWallet.isPresent()) {
            throw new WalletAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}
