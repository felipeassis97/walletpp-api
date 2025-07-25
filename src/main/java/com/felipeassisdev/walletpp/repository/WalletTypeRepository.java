package com.felipeassisdev.walletpp.repository;

import com.felipeassisdev.walletpp.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}