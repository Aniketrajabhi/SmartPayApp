package org.example.impl;

import com.wallet.wallet.model.Transaction;
import com.wallet.wallet.model.Wallet;
import com.wallet.wallet.repository.TransactionRepository;
import com.wallet.wallet.repository.WalletRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class WalletService {
    private final WalletRepository walletRepo;
    private final TransactionRepository txnRepo;
    private final KafkaTemplate<String, Object> kafka;

    public WalletService(WalletRepository walletRepo,
                         TransactionRepository txnRepo,
                         KafkaTemplate<String, Object> kafka) {
        this.walletRepo = walletRepo;
        this.txnRepo = txnRepo;
        this.kafka = kafka;
    }

    @Transactional
    public void transfer(String fromWalletId, String toWalletId, BigDecimal amount) {
        Wallet from = walletRepo.findById(fromWalletId).orElseThrow();
        Wallet to   = walletRepo.findById(toWalletId).orElseThrow();

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        walletRepo.save(from);
        walletRepo.save(to);

        Transaction txn = new Transaction();
        txn.setFromWallet(fromWalletId);
        txn.setToWallet(toWalletId);
        txn.setAmount(amount);
        txnRepo.save(txn);

        // publish event
        kafka.send("payments", txn);
    }
}