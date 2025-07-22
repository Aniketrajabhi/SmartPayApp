package org.example;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/pay")
public class PaymentController {

    @GetMapping("/")
    public String home() {
        return "Welcome to SmartPay!";
    }

    @PostMapping("/external")
    public PaymentResult charge(@RequestBody CardPaymentRequest req) {
        // mock
        return new PaymentResult("success", UUID.randomUUID().toString());
    }

    public record CardPaymentRequest(String cardNumber, BigDecimal amount) {}
    public record PaymentResult(String status, String transactionId) {}
}
