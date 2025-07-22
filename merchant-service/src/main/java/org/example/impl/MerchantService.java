package org.example.impl;

import org.example.Merchant;
import org.example.MerchantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MerchantService {
    private final MerchantRepository repo;

    public MerchantService(MerchantRepository repo) { this.repo = repo; }

    public Merchant onboard(String name, String email) {
        Merchant m = new Merchant();
        m.setName(name);
        m.setEmail(email);
        return repo.save(m);
    }

    public String createLink(String merchantId, BigDecimal amount) {
        return "https://pay.example.com/l/" + merchantId + "/" + amount;
    }
}
