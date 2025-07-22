package org.example;

import org.example.impl.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/wallet/{walletId}")
    public ResponseEntity<List<Transaction>> listByWallet(@PathVariable String walletId) {
        return ResponseEntity.ok(service.findByWallet(walletId));
    }
}
