package com.felipeassisdev.walletpp.repository;

import com.felipeassisdev.walletpp.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}