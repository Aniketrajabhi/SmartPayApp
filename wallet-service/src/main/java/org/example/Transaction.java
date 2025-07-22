package org.example;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    private String id = java.util.UUID.randomUUID().toString();
    private String fromWallet;
    private String toWallet;
    private BigDecimal amount;
    private Instant createdAt = Instant.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.COMPLETED;

    public enum Status { PENDING, COMPLETED, FAILED }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromWallet() {
        return fromWallet;
    }

    public void setFromWallet(String fromWallet) {
        this.fromWallet = fromWallet;
    }

    public String getToWallet() {
        return toWallet;
    }

    public void setToWallet(String toWallet) {
        this.toWallet = toWallet;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
