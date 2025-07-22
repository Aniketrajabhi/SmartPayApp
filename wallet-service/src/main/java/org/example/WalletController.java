package org.example;

import com.wallet.wallet.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final WalletService service;

    public WalletController(WalletService service) { this.service = service; }

    record TransferRequest(String fromWallet, String toWallet, BigDecimal amount) {}

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequest req) {
        service.transfer(req.fromWallet(), req.toWallet(), req.amount());
        return ResponseEntity.accepted().build();
    }
}
