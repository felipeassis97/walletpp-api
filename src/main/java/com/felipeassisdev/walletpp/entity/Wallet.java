package com.felipeassisdev.walletpp.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "cpf_cnpj", unique = true)
    private String cpfCnpj;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "wallet_type_id")
    private WalletType walletType;

    public Wallet(String fullName, String cpfCnpj, String email, String password, WalletType walletType) {
        this.fullName = fullName;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.password = password;
        this.walletType = walletType;
    }

    public boolean isTransferAllowed() {
        return this.walletType.getId().equals(WalletType.Enum.USER.get().getId());
    }

    public boolean isBalanceEqualOrGreaterThan(BigDecimal amount) {
        return this.balance.doubleValue() >= amount.doubleValue();
    }

    public void credit(@DecimalMin("0.01") BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void debit(@DecimalMin("0.01") BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) && Objects.equals(fullName, wallet.fullName) && Objects.equals(cpfCnpj, wallet.cpfCnpj) && Objects.equals(email, wallet.email) && Objects.equals(password, wallet.password) && Objects.equals(balance, wallet.balance) && Objects.equals(walletType, wallet.walletType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, cpfCnpj, email, password, balance, walletType);
    }

}
