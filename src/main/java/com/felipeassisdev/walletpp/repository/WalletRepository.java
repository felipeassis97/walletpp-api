package com.felipeassisdev.walletpp.repository;

import com.felipeassisdev.walletpp.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> findByCpfCnpjOrEmail(String cpfCnpj, String email);
}