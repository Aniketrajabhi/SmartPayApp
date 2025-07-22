package org.example.impl;

import org.example.Transaction;
import org.example.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<Transaction> findByWallet(String walletId) {
        return repo.findByFromWalletOrToWalletOrderByCreatedAtDesc(walletId, walletId);
    }
}
