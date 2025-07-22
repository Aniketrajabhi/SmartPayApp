package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByFromWalletOrToWalletOrderByCreatedAtDesc(String fromWallet, String toWallet);
}
