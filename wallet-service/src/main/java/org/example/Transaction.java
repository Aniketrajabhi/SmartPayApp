package org.example;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id private String id = java.util.UUID.randomUUID().toString();
    private String fromWallet;
    private String toWallet;
    private BigDecimal amount;
    private Instant createdAt = Instant.now();
    @Enumerated(EnumType.STRING)
    private Status status = Status.COMPLETED;

    public enum Status { PENDING, COMPLETED, FAILED }
    // getters / setters
}
