package org.example;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "wallets")
public class Wallet {
    @Id private String id;
    private Long userId;
    private BigDecimal balance;

    protected Wallet() {}
    public Wallet(Long userId) {
        this.id = java.util.UUID.randomUUID().toString();
        this.userId = userId;
        this.balance = BigDecimal.ZERO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
