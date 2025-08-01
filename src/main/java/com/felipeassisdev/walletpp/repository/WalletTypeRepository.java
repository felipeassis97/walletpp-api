package com.felipeassisdev.walletpp.repository;

import com.felipeassisdev.walletpp.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletTypeRepository extends JpaRepository<WalletType, UUID> {
}